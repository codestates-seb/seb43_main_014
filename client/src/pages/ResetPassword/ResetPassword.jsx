import React, { useState } from 'react';
import HelloBox from '../../components/common/HelloBox/HelloBox';
import Button from '../../components/common/Button/Button';
import styles from './ResetPassword.module.css';
import LabelInput from '../../components/common/LabelInput/LabelInput';
import FormBox from '../../components/common/FormBox/FormBox';
import { validate } from '../../utils/validate-resetpassword';

export default function ResetPassword() {
  const password = 'test1234!';
  const halfLength = Math.ceil(password.length / 2);
  const maskedPassword =
    password.substring(0, halfLength) + '*'.repeat(halfLength);

  const [form, setForm] = useState({
    phone: '',
  });

  const [errors, setErrors] = useState({
    phone: '',
  });

  const [valid, setValid] = useState({
    phone: false,
  });

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
    const [newErrors, newValid] = validate({ ...form, [name]: value });
    setErrors(newErrors);
    setValid(newValid);
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
          <div>비밀번호 : {maskedPassword}</div>
          <br />
          <br />
          <br />
          <Button allTrue={!allTrue} text="비밀번호 재설정" />
        </form>
      </FormBox>
    </main>
  );
}
