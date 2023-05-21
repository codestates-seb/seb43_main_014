import React, { useState } from 'react';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import Button from '../../components/common/Button/Button';
import styles from './ResetPassword.module.css';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import FormBox from '../../components/common/FormBox/FormBox';
import { validate } from '../../utils/validate-resetpassword';
import Alert from '../../components/common/Alert/Alert';
import axios from 'axios';
import ErrorRoundedIcon from '@mui/icons-material/ErrorRounded';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { isLoginState } from '../../recoil/AuthAtom';

export default function ResetPassword() {
  const navigate = useNavigate();

  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [form, setForm] = useState({
    email: '',
  });

  const [errors, setErrors] = useState({
    email: '',
  });

  const [valid, setValid] = useState({
    email: false,
  });

  const [showAlert, setShowAlert] = useState(false);

  const allTrue = Object.values(valid).every((value) => value === true);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (allTrue) {
      console.log('Form data:', form);
      axios
        .post(
          'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/forgot-password',
          form,
        )
        .then((res) => {
          console.log(res);
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

  // 로그인한 상태라면 비밀번호 찾기 페이지 못가게 홈페이지로 라우팅!
  if (isLogin) {
    navigate('/');
  }

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
          <div className={styles.title}>비밀번호 찾기</div>
          <div className={styles.explanation}>
            <p>회원가입 시 사용한 이메일을 입력해주세요.</p>
            <p>올바른 이메일이라면 임시 비밀번호를 발급 해드릴게요!</p>
          </div>
          <LabelInput
            labelText="이메일"
            type="text"
            name="email"
            placeholder="회원가입 시 사용한 이메일을 입력해주세요."
            value={form.email}
            handleChange={handleChange}
          />
          {form.email && (
            <div className={valid.email ? styles.successMsg : styles.errorsMsg}>
              {valid.email ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.email}</p>
            </div>
          )}

          <br />
          <br />
          <br />
          <br />
          <br />
          <Button allTrue={!allTrue} text="비밀번호 찾기" />
        </form>
      </FormBox>
    </main>
  );
}
