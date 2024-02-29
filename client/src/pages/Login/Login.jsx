import React, { useEffect, useState } from 'react';
import styles from './Login.module.css';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import FormBox from '../../components/common/FormBox/FormBox';
import Button from '../../components/common/Button/Button';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import { Link, useNavigate } from 'react-router-dom';
import Oauth from '../../components/common/Oauth/Oauth';
import { useRecoilState } from 'recoil';
import {
  isLoginState,
  refreshTokenState,
  tokenState,
  userState,
} from '../../recoil/AuthAtom';
import axios from 'axios';
import { validate } from '../../utils/validate-login';
import Alert from '../../components/common/Alert/Alert';
import ErrorRoundedIcon from '@mui/icons-material/ErrorRounded';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import {
  localStorageRemove,
  localStorageGet,
} from '../../utils/localstorageFunc';

export default function Login() {
  const navigate = useNavigate();

  const [token, setToken] = useRecoilState(tokenState);
  const [refreshToken, setRefreshToken] = useRecoilState(refreshTokenState);
  const [isLoginAtom, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);

  const [form, setForm] = useState({
    username: '',
    password: '',
  });
  const [errors, setErrors] = useState({
    username: '',
    password: '',
  });
  const [valid, setValid] = useState({
    username: false,
    password: false,
  });

  const [공백Alert, set공백Alert] = useState(false);
  const [세션Alert, set세션Alert] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  const [loginClicked, setLoginClicked] = useState(false);

  useEffect(() => {
    setLoginClicked(true);
  }, []);

  const allTrue = Object.values(valid).every((value) => value === true);

  const handleLogin = (e) => {
    e.preventDefault();
    if (allTrue) {
      axios
        .post(
          'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/auth/login',
          form,
        )
        .then((res) => {
          const refreshToken = res.headers.refresh;
          const token = res.headers.authorization.split(' ')[1];
          const userData = res.data;

          localStorage.setItem('jwt_token', token);
          localStorage.setItem('refresh_token', refreshToken);
          localStorage.setItem('user_info', JSON.stringify(userData));
          localStorage.setItem('isLogin', true);

          setToken(token);
          setRefreshToken(refreshToken);
          setIsLogin(true);
          setUserInfo(userData);

          navigate('/');

          // 리프레쉬 토큰 만료 타이머 설정
          const timerId = setTimeout(() => {
            const [access_token, refresh_token, ,] = localStorageGet();
            axios
              .post(
                'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/logout',
                {
                  accessToken: access_token,
                  refreshToken: refresh_token,
                },
              )
              .then((res) => {
                console.log(res);
                localStorageRemove();
                set세션Alert(true); // 비동기 관리 질문할 것
                alert(
                  '세션이 만료되어 자동 로그아웃되었습니다. 다시 로그인 해주세요 :)',
                );
                setToken(null);
                setRefreshToken(null);
                setUserInfo(null);
                setIsLogin(false);
                navigate('/login');
              });
          }, 604700000);
        })
        .catch((error) => {
          setShowAlert(true);
          console.log(error);
        });
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value.includes(' ')) {
      set공백Alert(true);
      e.preventDefault(); // 입력 막기
      return;
    }
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    const [newErrors, newValid] = validate({ ...form, [name]: value });
    setErrors(newErrors);
    setValid(newValid);
  };

  const [, , , is_Login] = localStorageGet();
  // 로그인한 상태라면 로그인 페이지로 가는게 아니라 홈페이지로 라우팅!
  if (is_Login) {
    navigate('/');
  }
  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        {공백Alert && (
          <Alert setShowAlert={set공백Alert}>
            <div>공백은 입력할 수 없습니다.</div>
          </Alert>
        )}
        <form className={styles.form} onSubmit={handleLogin}>
          <div className={styles.tapMenu}>
            <Link
              to="/login"
              className={`${styles.login} ${
                loginClicked && styles.loginClicked
              }`}
            >
              <h1>로그인</h1>
            </Link>
            <Link to="/signup" className={styles.signup}>
              <h1>회원가입</h1>
            </Link>
          </div>
          <LabelInput
            labelText="이메일"
            type="text"
            name="username"
            placeholder="이메일을 입력해주세요."
            value={form.username}
            handleChange={handleChange}
          />
          {form.username && (
            <div
              className={valid.username ? styles.successMsg : styles.errorsMsg}
            >
              {valid.username ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.username}</p>
            </div>
          )}
          <LabelInput
            labelText="비밀번호"
            type="password"
            name="password"
            placeholder="비밀번호를 입력해주세요."
            resetButton={true}
            value={form.password}
            handleChange={handleChange}
          />
          {form.password && (
            <div
              className={valid.password ? styles.successMsg : styles.errorsMsg}
            >
              {valid.password ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.password}</p>
            </div>
          )}
          <Button allTrue={!allTrue} text="로그인" />
        </form>
        <Oauth />
        {showAlert && (
          <Alert setShowAlert={setShowAlert}>
            <div>아이디와 비밀번호를 다시 확인해주세요!</div>
          </Alert>
        )}
        {세션Alert && (
          <Alert>
            <div>
              세션이 만료되어 자동 로그아웃되었습니다. 다시 로그인 해주세요 :)
            </div>
          </Alert>
        )}
      </FormBox>
    </main>
  );
}
