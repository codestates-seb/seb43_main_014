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
import { tokenState, isLoginState, userState } from '../../recoil/AuthAtom';

export default function OAuthLogin() {
  const navigate = useNavigate();

  const [token, setToken] = useRecoilState(tokenState);
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

  function extractAccessToken(url) {
    const queryString = url.split('?')[1];
    const params = new URLSearchParams(queryString);
    console.log(params);
    const accessTokenParam = params.get('accessToken');
    console.log('❌', accessTokenParam);

    if (accessTokenParam) {
      // 액세스 토큰에서 'Bearer '를 제거한 값을 반환
      return accessTokenParam.replace('Bearer ', '');
    }

    return null; // 액세스 토큰이 없는 경우 null을 반환
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const currentUrl = window.location.href;
    const accessToken = extractAccessToken(currentUrl);

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

          setToken(accessToken); // 토큰을 리코일 상태에 저장
          setIsLogin(true); // 로그인 상태 리코일 상태에 저장
          setUserInfo(userData); // 유저 데이터 리코일 상태에 저장

          localStorage.setItem('jwt_token', accessToken);
          localStorage.setItem('user_info', JSON.stringify(userData));
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
          <div className={valid.phone ? styles.successMsg : styles.errorsMsg}>
            {errors.phone}
          </div>
          <br />
          <br />
          <br />
          <Button allTrue={!allTrue} text="휴대폰 번호 추가" />
        </form>
      </FormBox>
    </main>
  );
}
