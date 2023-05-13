import React, { useState } from 'react';
import styles from './Signup.module.css';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import FormBox from '../../components/common/FormBox/FormBox';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import Button from '../../components/common/Button/Button';
import Oauth from '../../components/common/Oauth/Oauth';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { validate } from '../../utils/validate-signup';
import Alert from '../../components/common/Alert/Alert';

export default function Signup() {
  const navigate = useNavigate();
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

  const [showAlert, setShowAlert] = useState(false);

  const allTrue = Object.values(valid).every((value) => value === true);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (allTrue) {
      console.log('Form data:', form);
      axios
        .post(
          'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user',
          form,
        )
        .then((res) => {
          console.log(res);
          alert('회원가입에 성공하셨습니다.');
          navigate('/login');
        })
        .catch(() => {
          alert('회원가입에 실패하였습니다.');
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
    console.log({ ...form, [name]: value });
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    // 문제발생: 최신 form value를 전달받지 못함(setState가 비동기로 동작)
    // 해결방법: form value를 업데이트해서 전달
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
