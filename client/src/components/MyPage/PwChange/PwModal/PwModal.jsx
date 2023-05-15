import React, { useState } from 'react';
import styles from './pwModal.module.css';
import Modal from '../../../common/Modal';
import { Link } from 'react-router-dom';
import axios from 'axios';

const PwModal = ({ openModalHandler }) => {
  const [newPassword, setNewPassword] = useState({
    password_current: '',
    password: '',
    password_confirm: '',
  });

  const [errors, setErrors] = useState({
    password: '',
    password_confirm: '',
  });

  const [valid, setValid] = useState({
    password: false,
    password_confirm: false,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value.includes(' ')) {
      // 공백이 포함되어 있다면
      alert('공백은 입력할 수 없습니다.');
      e.preventDefault(); // 입력 막기
      return;
    }
    setNewPassword((newPassword) => ({ ...newPassword, [name]: value }));
    validate({ ...newPassword, [name]: value });
    console.log({ ...newPassword, [name]: value });
    console.log(e.target);
    console.log(value);
  };

  const handleSubmit = (e) => {
    const { password_current, password, password_confirm } = newPassword;
    // console.log(newPassword);
    if (password_current && password && password_confirm) {
      axios.patch(``, {
        currentPassword: password_current,
        newPassword: password,
      });
      console.log('fatch 요청');
    } else {
      e.preventDefault();
    }
  };

  const validate = (newPassword) => {
    const newErrors = {
      password: '',
      password_confirm: '',
    };

    if (newPassword.password.trim() === '') {
      newErrors.password = '비밀번호 (영문자, 숫자, 특수문자 포함 최소 8~20자)';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else if (newPassword.password.length < 8) {
      newErrors.password = '비밀번호는 8자리 이상이어야 합니다.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else if (
      !/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/.test(
        newPassword.password,
      )
    ) {
      newErrors.password = '비밀번호 (영문자, 숫자, 특수문자 포함 최소 8~20자)';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else {
      newErrors.password = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, password: true }));
    }

    if (newPassword.password_confirm.trim() === '') {
      newErrors.password_confirm = '비밀번호를 다시 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, password_confirm: false }));
    } else if (newPassword.password !== newPassword.password_confirm) {
      newErrors.password_confirm = '비밀번호가 일치하지 않습니다.';
      setValid((prevValid) => ({ ...prevValid, password_confirm: false }));
    } else if (newPassword.password === newPassword.password_confirm) {
      newErrors.password_confirm = '비밀번호가 일치합니다.';
      setValid((prevValid) => ({ ...prevValid, password_confirm: true }));
    }

    setErrors(newErrors);
  };

  const allTrue = Object.values(valid).every((value) => value === true);
  return (
    <>
      <Modal openModalHandler={openModalHandler}>
        <div>
          <h4>비밀번호 변경</h4>
        </div>
        <div>
          <form>
            <div className={styles.modalChangeInput}>
              <span>현재 비밀번호</span>
              <input
                type="password"
                name="password_current"
                onChange={handleChange}
              />
              <div>
                <span className={styles.inputDetail}>
                  비밀번호를 잊으셨나요?{' '}
                </span>
                <Link to="/reset_password">
                  <span className={styles.resetLink}>비밀번호 재설정</span>
                </Link>
              </div>
            </div>
            <div className={styles.modalChangeInput}>
              <span>비밀번호</span>
              <input type="password" name="password" onChange={handleChange} />
              <div
                className={
                  valid.password ? styles.successMsg : styles.errorsMsg
                }
              >
                {errors.password}
              </div>
            </div>
            <div className={styles.modalChangeInput}>
              <span>비밀번호 확인</span>
              <input
                type="password"
                name="password_confirm"
                onChange={handleChange}
              />
              <div
                className={
                  valid.password_confirm ? styles.successMsg : styles.errorsMsg
                }
              >
                {errors.password_confirm}
              </div>
            </div>
            <div className={styles.modalChangeBtn}>
              <button
                id={styles.cancel}
                className={styles.btn}
                onClick={openModalHandler}
              >
                취소
              </button>
              {newPassword.password_current && allTrue ? (
                <button
                  type="submit"
                  className={styles.btn}
                  onClick={handleSubmit}
                >
                  저장
                </button>
              ) : (
                <button disabled className={styles.notBtn}>
                  저장
                </button>
              )}
            </div>
          </form>
        </div>
      </Modal>
    </>
  );
};

export default PwModal;
