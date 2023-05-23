import React, { useState } from 'react';
import Button from '../../components/common/Button/Button';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import FormBox from '../../components/common/FormBox/FormBox';
import { validate } from '../../utils/validate-OAuthPhone';
import Alert from '../../components/common/Alert/Alert';
import axios from 'axios';
import styles from './OAuthLogin.module.css';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import {
  tokenState,
  isLoginState,
  userState,
  refreshTokenState,
} from '../../recoil/AuthAtom';
import { extractAuth } from '../../utils/extractAuth';
import ErrorRoundedIcon from '@mui/icons-material/ErrorRounded';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import { localStorageGet } from '../../utils/localstorageFunc';

export default function OAuthLogin() {
  const navigate = useNavigate();

  const [token, setToken] = useRecoilState(tokenState);
  const [refreshToken, setRefreshToken] = useRecoilState(refreshTokenState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);

  const [form, setForm] = useState({
    phone: '',
  });
  const [errors, setErrors] = useState({
    phone: '',
  });
  const [valid, setValid] = useState({
    phone: false,
  });

  const [showAlert, setShowAlert] = useState(false);

  const allTrue = Object.values(valid).every((value) => value === true);

  const handleSubmit = (e) => {
    e.preventDefault();
    const currentUrl = window.location.href;
    const [accessToken, refreshToken, ,] = extractAuth(currentUrl);

    if (allTrue) {
      axios
        .post(
          'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/oauth2/phone',
          form,
          {
            headers: { Authorization: `Bearer ${accessToken}` },
          },
        )
        .then((res) => {
          const userData = res.data;

          localStorage.setItem('jwt_token', accessToken);
          localStorage.setItem('refresh_token', refreshToken);
          localStorage.setItem('isLogin', true);
          localStorage.setItem('user_info', JSON.stringify(userData));

          setToken(accessToken);
          setRefreshToken(refreshToken);
          setIsLogin(true);
          setUserInfo(userData);

          navigate('/');
        })
        .catch((error) => {
          alert('OAuth 로그인 실패!');
          console.error(error);
        });
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value.includes(' ')) {
      setShowAlert(true);
      e.preventDefault();
      return;
    }
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    const [newErrors, newValid] = validate({ ...form, [name]: value });
    setErrors(newErrors);
    setValid(newValid);
  };

  const [, , , is_Login] = localStorageGet();

  if (is_Login) {
    navigate('/');
  }

  return (
    <main className={styles.container}>
      <FormBox>
        {showAlert && (
          <Alert setShowAlert={setShowAlert}>
            <div>공백은 입력할 수 없습니다.</div>
          </Alert>
        )}
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.title}>휴대폰 번호 추가</div>
          <div className={styles.explanation}>
            <p>로켓CV에 처음 오셨군요!</p>
            <p>우선, 로켓CV에서 사용하실 휴대폰 번호를 추가해주세요!</p>
          </div>
          <LabelInput
            labelText="휴대폰 번호"
            type="text"
            name="phone"
            placeholder="휴대폰 번호를 입력해주세요."
            value={form.phone}
            handleChange={handleChange}
          />
          {form.phone && (
            <div className={valid.phone ? styles.successMsg : styles.errorsMsg}>
              {valid.phone ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.phone}</p>
            </div>
          )}
          <br />
          <br />
          <br />
          <Button allTrue={!allTrue} text="휴대폰 번호 추가" />
        </form>
      </FormBox>
    </main>
  );
}
