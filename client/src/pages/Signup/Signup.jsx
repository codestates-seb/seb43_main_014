import React, { useEffect, useState } from 'react';
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
import ErrorRoundedIcon from '@mui/icons-material/ErrorRounded';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import { localStorageGet } from '../../utils/localstorageFunc';

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

  const [공백Alert, set공백Alert] = useState(false);
  const [회원가입실패모달, set회원가입실패모달] = useState(false);
  const [회원가입성공모달, set회원가입성공모달] = useState(false);
  const [signupClicked, setSignupClicked] = useState(false);

  useEffect(() => {
    setSignupClicked(true);
  }, []);

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
          set회원가입성공모달(true);
          setTimeout(() => {
            navigate('/login');
          }, 3000);
        })
        .catch((error) => {
          console.log(error);
          set회원가입실패모달(true);
        });
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value.includes(' ')) {
      set공백Alert(true);
      e.preventDefault();
      return;
    }
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    // 문제발생: 최신 form value를 전달받지 못함(setState가 비동기로 동작)
    // 해결방법: form value를 업데이트해서 전달
    const [newErrors, newValid] = validate({ ...form, [name]: value });
    setErrors(newErrors);
    setValid(newValid);
  };

  const [, , , is_Login] = localStorageGet();
  // 로그인한 상태라면 회원가입 페이지로 가는게 아니라 홈페이지로 라우팅!
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
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.tapMenu}>
            <Link to="/login" className={styles.login}>
              <h1>로그인</h1>
            </Link>
            <Link
              to="/signup"
              className={`${styles.signup} ${
                signupClicked && styles.signupClicked
              }`}
            >
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
          {form.name && (
            <div className={valid.name ? styles.successMsg : styles.errorsMsg}>
              {valid.name ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.name}</p>
            </div>
          )}
          <LabelInput
            labelText="휴대폰 번호"
            type="text"
            name="phone"
            placeholder="휴대폰 번호를 입력해주세요."
            phoneDuplicate={true}
            value={form.phone}
            handleChange={handleChange}
          />
          {form.phone && (
            <div className={valid.phone ? styles.successMsg : styles.errorsMsg}>
              {valid.phone ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.phone}</p>
            </div>
          )}
          <LabelInput
            labelText="이메일"
            type="text"
            name="email"
            placeholder="이메일을 입력해주세요."
            emailDuplicate={true}
            value={form.email}
            handleChange={handleChange}
          />
          {form.email && (
            <div className={valid.email ? styles.successMsg : styles.errorsMsg}>
              {valid.email ? <CheckCircleIcon /> : <ErrorRoundedIcon />}
              <p>{errors.email}</p>
            </div>
          )}
          <LabelInput
            labelText="비밀번호"
            type="password"
            name="password"
            placeholder="영문자, 숫자, 특수문자 포함 8글자 이상 입력해주세요."
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
          <LabelInput
            isNoLabel={true}
            type="password"
            name="password_confirm"
            placeholder="위에 입력하신 비밀번호를 똑같이 입력해주세요."
            value={form.password_confirm}
            handleChange={handleChange}
          />
          {form.password_confirm && (
            <div
              className={
                valid.password_confirm ? styles.successMsg : styles.errorsMsg
              }
            >
              {valid.password_confirm ? (
                <CheckCircleIcon />
              ) : (
                <ErrorRoundedIcon />
              )}
              <p>{errors.password_confirm}</p>
            </div>
          )}

          <Button allTrue={!allTrue} text="회원가입" />
        </form>
        <Oauth />
        {회원가입실패모달 && (
          <Alert setShowAlert={set회원가입실패모달}>
            <div>휴대폰 번호와 이메일 중복확인을 하셨나요?</div>
          </Alert>
        )}
        {회원가입성공모달 && (
          <Alert isSuccess={true} setShowAlert={set회원가입성공모달}>
            <div>
              회원가입에 성공하셨습니다! 로켓CV의 회원이 되신걸 축하드려요!
            </div>
            <br />
            <br />
            <br />
            <div>3초뒤에 로그인페이지로 넘어가요!</div>
          </Alert>
        )}
      </FormBox>
    </main>
  );
}
