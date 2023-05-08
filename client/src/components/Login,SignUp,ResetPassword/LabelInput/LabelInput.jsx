import React from 'react';
import styles from './LabelInput.module.css';
import { Link } from 'react-router-dom';

export default function LabelInput({
  labelText,
  duplicate,
  isNoLabel,
  resetButton,
  type,
  name,
  placeholder,
  value,
  handleChange,
}) {
  // 라벨없는 인풋창! (ex.비밀번호 확인)
  if (isNoLabel) {
    return (
      <div className={styles.box}>
        <div style={{ marginTop: '0.4rem' }}></div>
        <input
          className={styles.input}
          name={name}
          type={type}
          placeholder={placeholder}
          value={value}
          onChange={handleChange}
        />
      </div>
    );
  }

  // 기본 라벨+인풋창
  return (
    <div className={styles.box}>
      <div className={styles.startEnd}>
        <label htmlFor={name} className={styles.label}>
          {labelText}
        </label>
        {resetButton && (
          <Link to="/reset_password" className={styles.passwordReset}>
            비밀번호 재설정
          </Link>
        )}
        {duplicate && <div className={styles.duplicate}>중복확인</div>}
      </div>
      <input
        className={styles.input}
        id={name}
        name={name}
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={handleChange}
      />
    </div>
  );
}
