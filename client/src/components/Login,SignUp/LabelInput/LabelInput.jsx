import React, { useState } from 'react';
import styles from './LabelInput.module.css';
import { Link } from 'react-router-dom';

export default function LabelInput({
  labelText,
  duplicate,
  isNoLabel,
  signup,
  login,
  type,
  name,
}) {
  const [form, setForm] = useState({ name: '', email: '', password: '' });

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(name, e.target.value);
    setForm({ ...form, [name]: value });
  };

  // 라벨없는 인풋창! (비밀번호 확인)
  if (isNoLabel) {
    return (
      <div className={styles.box}>
        <div style={{ marginTop: '0.4rem' }}></div>
        <input
          name={name}
          type="password"
          className={styles.input}
          placeholder={`비밀번호 확인 해주세요.`}
          onChange={handleChange}
        />
      </div>
    );
  }

  // 회원가입 페이지 비밀번호 인풋창
  if (signup) {
    return (
      <div className={styles.box}>
        <div className={styles.password_menu}>
          <label htmlFor={name} className={styles.label}>
            비밀번호
          </label>
        </div>
        <input
          id={name}
          name={name}
          type="password"
          className={styles.input}
          placeholder="영문자, 숫자, 특수문자 포함 8글자 이상"
          onChange={handleChange}
        />
      </div>
    );
  }

  // 로그인 페이지에서 비밀번호 인풋창 (비밀번호 재설정 버튼 추가됨)
  if (login) {
    return (
      <div className={styles.box}>
        <div className={styles.password_menu}>
          <label htmlFor={name} className={styles.label}>
            비밀번호
          </label>
          <Link to="/reset_password" className={styles.passwordReset}>
            비밀번호 재설정
          </Link>
        </div>
        <input
          id={name}
          name={name}
          type="password"
          className={styles.input}
          placeholder="비밀번호 입력해주세요."
          onChange={handleChange}
        />
      </div>
    );
  }

  // 라벨+인풋창
  return (
    <div className={styles.box}>
      <div className={styles.startEnd}>
        <label htmlFor={name} className={styles.label}>
          {labelText}
        </label>
        {duplicate && <div className={styles.duplicate}>중복확인</div>}
      </div>
      <input
        id={name}
        name={name}
        type={type}
        className={styles.input}
        placeholder={`${labelText} 입력해주세요.`}
        onChange={handleChange}
      />
    </div>
  );
}
