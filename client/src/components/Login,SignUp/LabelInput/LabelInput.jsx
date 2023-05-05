import React from 'react';
import styles from './LabelInput.module.css';
import { Link, useNavigate } from 'react-router-dom';

export default function LabelInput({ label, duplicate }) {
  const currentUrl = window.location.href;
  const navigate = useNavigate();

  // 비밀번호 확인 인풋창 라벨없음
  if (label === '비밀번호 확인') {
    return (
      <div className={styles.box}>
        <div style={{ marginTop: '0.4rem' }}></div>
        <input
          type="password"
          className={styles.input}
          placeholder="비밀번호를 확인 해주세요."
        />
      </div>
    );
  }

  // 우측 상단에 비밀번호 재설정 있는 인풋창
  if (label === '비밀번호') {
    return (
      <div className={styles.box}>
        <div className={styles.password_menu}>
          <label htmlFor="" className={styles.label}>
            {label}
          </label>
          {currentUrl === 'http://localhost:3000/login' && (
            <Link className={styles.passwordReset}>
              <div
                onClick={() => {
                  navigate('/reset_password');
                }}
              >
                비밀번호 재설정
              </div>
            </Link>
          )}
        </div>
        <input
          type="password"
          className={styles.input}
          placeholder={
            currentUrl === 'http://localhost:3000/signup'
              ? '영문자, 숫자, 특수문자 포함 8글자 이상'
              : '비밀번호를 입력해주세요.'
          }
        />
      </div>
    );
  }

  // 라벨+인풋창
  return (
    <div className={styles.box}>
      <div className={styles.startEnd}>
        <label htmlFor="" className={styles.label}>
          {label}
        </label>
        {currentUrl === 'http://localhost:3000/signup' && duplicate && (
          <div className={styles.duplicate}>중복확인</div>
        )}
      </div>
      <input
        type="text"
        className={styles.input}
        placeholder={`${label}을 입력해주세요.`}
      />
    </div>
  );
}
