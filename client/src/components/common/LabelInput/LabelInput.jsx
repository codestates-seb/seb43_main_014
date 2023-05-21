import React, { useState } from 'react';
import styles from './LabelInput.module.css';
import { Link } from 'react-router-dom';
import Modal from '../../common/Modal/index';
import Button from '../Button/Button';
import axios from 'axios';

export default function LabelInput({
  labelText,
  phoneDuplicate,
  emailDuplicate,
  isNoLabel,
  resetButton,
  type,
  name,
  placeholder,
  value,
  handleChange,
}) {
  const [isOpenModal, setIsOpenModal] = useState(false);

  const valid = true;

  const handleClickModal = () => {
    setIsOpenModal((isOpen) => !isOpen);
  };
  console.log(value);

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
            비밀번호 찾기
          </Link>
        )}
        {(emailDuplicate || phoneDuplicate) && (
          <div
            className={styles.duplicate}
            onClick={() => {
              setIsOpenModal(true);
              axios.post(
                `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/sign/${name}`,
                { name: value },
              );
            }}
          >
            중복확인
          </div>
        )}
      </div>
      {phoneDuplicate && isOpenModal && (
        <Modal openModalHandler={handleClickModal}>
          {valid ? (
            <div className={styles.center}>
              <p>사용 가능한 휴대폰 번호입니다.</p>
              <Button text="사용하기" />
            </div>
          ) : (
            <div className={styles.center}>
              <p>이미 존재하는 휴대폰 번호입니다.</p>
              <Button text="다른 휴대폰 번호 사용하기" />
            </div>
          )}
        </Modal>
      )}
      {emailDuplicate && isOpenModal && (
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
