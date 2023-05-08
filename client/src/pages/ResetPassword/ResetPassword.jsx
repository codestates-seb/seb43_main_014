import React, { useState } from 'react';
import HelloBox from '../../components/Login,SignUp,ResetPassword/HelloBox/HelloBox';
import Button from '../../components/Login,SignUp,ResetPassword/Button/Button';
import styles from './ResetPassword.module.css';
import LabelInput from '../../components/Login,SignUp,ResetPassword/LabelInput/LabelInput';
import FormBox from '../../components/Login,SignUp,ResetPassword/FormBox/FormBox';

export default function ResetPassword() {
  const [form, setForm] = useState({
    phone: '',
  });

  const [errors, setErrors] = useState({
    phone: '',
  });

  const [valid, setValid] = useState({
    phone: false,
  });

  const validate = (form) => {
    const newErrors = {
      phone: '',
    };

    if (form.phone.trim() === '') {
      newErrors.phone = '휴대폰 번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else if (
      !/^01([016789]{1}|[5]{1}[0123456789]{1})(\d{3}|\d{4})(\d{4})$/.test(
        form.phone,
      )
    ) {
      newErrors.phone = '올바른 휴대폰 번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else {
      newErrors.phone = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, phone: true }));
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
    console.log(form);
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    validate({ ...form, [name]: value });
  };

  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.title}>비밀번호 재설정</div>
          <div className={styles.explanation}>
            <p>회원가입 시 사용한 휴대폰 번호를 입력해주세요.</p>
            <p>올바른 번호면 비밀번호를 알려드립니다.</p>
          </div>
          <LabelInput
            labelText="휴대폰 번호"
            type="text"
            name="phone"
            placeholder="회원가입 시 사용한 휴대폰 번호를 입력해주세요."
            value={form.phone}
            handleChange={handleChange}
          />
          <div className={valid.phone ? styles.successMsg : styles.errorsMsg}>
            {errors.phone}
          </div>
          <br />
          <br />
          <br />
          <Button allTrue={!allTrue} text="비밀번호 재설정" />
        </form>
      </FormBox>
    </main>
  );
}
