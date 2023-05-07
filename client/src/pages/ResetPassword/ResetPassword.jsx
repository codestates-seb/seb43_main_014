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

  const handleChange = (e) => {
    const { name, value } = e.target;
    // console.log(name, e.target.value);
    console.log(form);
    setForm({ ...form, [name]: value });
  };

  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        <form className={styles.form}>
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
          <br />
          <br />
          <br />
          <Button text="비밀번호 재설정" />
        </form>
      </FormBox>
    </main>
  );
}
