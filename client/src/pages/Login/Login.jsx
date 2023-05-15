import React, { useState } from 'react';
import styles from './Login.module.css';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import FormBox from '../../components/common/FormBox/FormBox';
import Button from '../../components/common/Button/Button';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import { Link, useNavigate } from 'react-router-dom';
import Oauth from '../../components/common/Oauth/Oauth';
import { useRecoilState } from 'recoil';
import { isLoginState, tokenState } from '../../recoil/TokenAtom';
import { userState } from '../../recoil/userAtom';
import axios from 'axios';
import { validate } from '../../utils/validate-login';
import Alert from '../../components/common/Alert/Alert';

export default function Login() {
  const navigate = useNavigate();

  const [token, setToken] = useRecoilState(tokenState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);
  console.log(isLogin); // 로그인시 : false -> true
  console.log('userInfo : ', userInfo);

  // InputValueState
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

  const [showAlert, setShowAlert] = useState(false);

  const allTrue = Object.values(valid).every((value) => value === true);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (allTrue) {
      console.log('Form data:', form);
      axios
        .post(
          'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/auth/login',
          form,
        )
        .then((res) => {
          // 토큰 값을 Recoil 상태로 업데이트합니다.
          const token = res.headers.authorization.split(' ')[1]; // "Bearer " 부분을 제외한 토큰 값만 추출
          setToken(token); // 토큰을 리코일 상태에 저장
          setIsLogin(true); // 로그인 상태 리코일 상태에 저장
          setUserInfo(res.data); // 유저 데이터 리코일 상태에 저장
          localStorage.setItem('jwt_token', token);
          const userData = res.data;
          localStorage.setItem('user_info', JSON.stringify(userData));
          navigate('/');
        })
        .catch(() => {
          alert('로그인 실패!');
        });
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value.includes(' ')) {
      setShowAlert(true);
      e.preventDefault(); // 입력 막기
      return;
    }
    console.log({ ...form, [name]: value });
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    const [newErrors, newValid] = validate({ ...form, [name]: value });
    setErrors(newErrors);
    setValid(newValid);
  };

  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        {showAlert && (
          <Alert setShowAlert={setShowAlert}>
            <div>공백은 입력할 수 없습니다.</div>
          </Alert>
        )}
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.tapMenu}>
            <Link to="/login" className={styles.login}>
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
          <div
            className={valid.username ? styles.successMsg : styles.errorsMsg}
          >
            {errors.username}
          </div>
          <LabelInput
            labelText="비밀번호"
            type="password"
            name="password"
            placeholder="비밀번호를 입력해주세요."
            resetButton={true}
            value={form.password}
            handleChange={handleChange}
          />
          <div
            className={valid.password ? styles.successMsg : styles.errorsMsg}
          >
            {errors.password}
          </div>
          <Button allTrue={!allTrue} text="로그인" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
