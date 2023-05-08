import React, { useState } from 'react';
import styles from './Login.module.css';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import FormBox from '../../components/common/FormBox/FormBox';
import Button from '../../components/common/Button/Button';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import { Link, useNavigate } from 'react-router-dom';
import Oauth from '../../components/common/Oauth/Oauth';
import { useSetRecoilState } from 'recoil';
import { TokenAtom } from '../../recoil/TokenAtom';
import axios from 'axios';

export default function Login() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    email: '',
    password: '',
  });

  const [errors, setErrors] = useState({
    email: '',
    password: '',
  });

  const [valid, setValid] = useState({
    email: false,
    password: false,
  });

  const validate = (form) => {
    const newErrors = {
      email: '',
      password: '',
    };

    if (form.email.trim() === '') {
      newErrors.email = '이메일을 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, email: false }));
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(form.email)) {
      newErrors.email = '올바른 이메일 형식이 아닙니다.';
      setValid((prevValid) => ({ ...prevValid, email: false }));
    } else {
      newErrors.email = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, email: true }));
    }

    if (form.password.trim() === '') {
      newErrors.password = '비밀번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else if (form.password.length < 8) {
      newErrors.password = '비밀번호는 8자리 이상이어야 합니다.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else if (
      !/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/.test(form.password)
    ) {
      newErrors.password =
        '영문자, 숫자, 특수문자를 포함한 8글자 이상의 비밀번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else {
      newErrors.password = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, password: true }));
    }
    setErrors(newErrors);
  };

  const allTrue = Object.values(valid).every((value) => value === true);

  const setAccessToken = useSetRecoilState(TokenAtom);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (allTrue) {
      // 유효성 검사에 성공하면 폼 데이터를 서버로 보냅니다.
      console.log('Form data:', form);
      axios.post('API/login', form).then((res) => {
        console.log(res.data);
        setAccessToken(res.data.accessToken);
        navigate('/');
      });
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value.includes(' ')) {
      // 공백이 포함되어 있다면
      alert('공백은 입력할 수 없습니다.');
      e.preventDefault(); // 입력 막기
      return;
    }
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    validate({ ...form, [name]: value });
  };

  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
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
            name="email"
            placeholder="이메일을 입력해주세요."
            value={form.email}
            handleChange={handleChange}
          />
          <div className={valid.email ? styles.successMsg : styles.errorsMsg}>
            {errors.email}
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
