import React, { useState } from 'react';
import styles from './LabelInput.module.css';
import { Link } from 'react-router-dom';
import Modal from '../../common/Modal/index';
import Button from '../Button/Button';

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
  const [isOpenModal, setIsOpenModal] = useState(false);

  const valid = false;

  const handleClickModal = () => {
    setIsOpenModal((prev) => !prev);
  };
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
        {duplicate && (
          <div className={styles.duplicate} onClick={handleClickModal}>
            중복확인
          </div>
        )}
      </div>
      {isOpenModal && (
        <Modal openModalHandler={handleClickModal}>
          {valid ? (
            <div className={styles.center}>
              <p>사용 가능한 이메일입니다.</p>
              <Button text="사용하기" />
            </div>
          ) : (
            <div className={styles.center}>
              <p>이미 존재하는 이메일입니다.</p>
              <Button text="다른 이메일 작성하기" />
            </div>
          )}
        </Modal>
      )}
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
