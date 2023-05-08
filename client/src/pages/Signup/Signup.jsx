import React, { useState } from 'react';
import styles from './Signup.module.css';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import FormBox from '../../components/common/FormBox/FormBox';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import Button from '../../components/common/Button/Button';
import Oauth from '../../components/common/Oauth/Oauth';
import { Link } from 'react-router-dom';

export default function Signup() {
  const [form, setForm] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    password_confirm: '',
  });

  const [errors, setErrors] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    password_confirm: '',
  });

  const [valid, setValid] = useState({
    name: false,
    phone: false,
    email: false,
    password: false,
    password_confirm: false,
  });
  console.log(valid);
  console.log(form);

  const validate = (form) => {
    const newErrors = {
      name: '',
      phone: '',
      email: '',
      password: '',
      password_confirm: '',
    };
    if (!/^[\uac00-\ud7a3]+$/.test(form.name)) {
      newErrors.name = '이름은 한글만 입력 가능합니다.';
      setValid((prevValid) => ({ ...prevValid, name: false }));
    } else if (!/^(?=.{2,20}$)[가-힣]{2,20}$/.test(form.name)) {
      newErrors.name = '이름은 2~20자 사이의 한글만 입력 가능합니다.';
      setValid((prevValid) => ({ ...prevValid, name: false }));
    } else {
      newErrors.name = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, name: true }));
    }

    if (form.phone.trim() === '') {
      newErrors.phone = '휴대폰 번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else if (
      !/^01([016789]{1}|[5]{1}[0123456789]{1})(\d{3}|\d{4})(\d{4})$/.test(
        form.phone,
      )
    ) {
      newErrors.phone = '"-" 하이픈은 빼고 입력해주세요!';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else {
      newErrors.phone = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, phone: true }));
    }

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

    if (form.password_confirm.trim() === '') {
      newErrors.password_confirm = '비밀번호를 다시 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, password_confirm: false }));
    } else if (form.password !== form.password_confirm) {
      newErrors.password_confirm = '비밀번호가 일치하지 않습니다.';
      setValid((prevValid) => ({ ...prevValid, password_confirm: false }));
    } else if (form.password === form.password_confirm) {
      newErrors.password_confirm = '비밀번호가 일치합니다.';
      setValid((prevValid) => ({ ...prevValid, password_confirm: true }));
    }
    setErrors(newErrors);
  };

  const allTrue = Object.values(valid).every((value) => value === true);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (allTrue) {
      // 유효성 검사에 성공하면 폼 데이터를 서버로 보냅니다.
      console.log('Form data:', form);
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
    // 문제발생: 최신 form value를 전달받지 못함(setState가 비동기로 동작)
    // 해결방법: form value를 업데이트해서 전달
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
            labelText="이름"
            type="text"
            name="name"
            placeholder="이름을 입력해주세요."
            value={form.name}
            handleChange={handleChange}
          />
          <div className={valid.name ? styles.successMsg : styles.errorsMsg}>
            {errors.name}
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
          <LabelInput
            labelText="이메일"
            type="text"
            name="email"
            placeholder="이메일을 입력해주세요."
            duplicate={true}
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
            placeholder="영문자, 숫자, 특수문자 포함 8글자 이상 입력해주세요."
            value={form.password}
            handleChange={handleChange}
          />
          <div
            className={valid.password ? styles.successMsg : styles.errorsMsg}
          >
            {errors.password}
          </div>
          <LabelInput
            isNoLabel={true}
            type="password"
            name="password_confirm"
            placeholder="위에 입력하신 비밀번호를 똑같이 입력해주세요."
            value={form.password_confirm}
            handleChange={handleChange}
          />
          <div
            className={
              valid.password_confirm ? styles.successMsg : styles.errorsMsg
            }
          >
            {errors.password_confirm}
          </div>
          <Button allTrue={!allTrue} text="회원가입" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
