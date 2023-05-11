import React, { useState } from 'react';
import styles from './profileUpdata.module.css';
import axios from 'axios';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';

const ProfileUpdata = ({ inputs, setInputs, setInfoUpdata }) => {
  const { name, email, phone } = inputs;
  const [errors, setErrors] = useState({
    phone: '',
  });

  const [valid, setValid] = useState({
    phone: false,
  });
  const onSubmit = () => {
    axios.patch(``, {});
    console.log('asd');
  };

  const validate = () => {
    const newErrors = {
      phone: '',
    };

    if (phone.trim() === '') {
      newErrors.phone = '휴대폰 번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else if (
      !/^01([016789]{1}|[5]{1}[0123456789]{1})(\d{3}|\d{4})(\d{4})$/.test(phone)
    ) {
      newErrors.phone = '올바른 휴대폰 번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else {
      newErrors.phone = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, phone: true }));
    }
    setErrors(newErrors);
  };

  const onChange = (e) => {
    const { value, name } = e.target;
    setInputs({
      ...inputs, // 기존의 input 객체를 복사한 뒤
      [name]: value, // name 키를 가진 값을 value 로 설정
    });
    validate({ ...inputs, [name]: value });
    console.log({ ...inputs, [name]: value });
  };
  return (
    <>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img
              className={styles.pic}
              src="https://mediaim.expedia.com/localexpert/1391601/fe50a3dc-b95f-4815-a331-05cbbc16d855.jpg?impolicy=resizecrop&rw=1005&rh=565"
            />
            <div>
              <Fab size="small" color="primary" aria-label="add">
                <AddIcon />
              </Fab>
            </div>
          </div>
          <div className={styles.proInfo}>
            <div className={styles.info}>
              <span>이름</span>
              <div className={styles.updataInput}>
                <input
                  name="name"
                  type="text"
                  value={name}
                  onChange={onChange}
                />
              </div>
            </div>
            <div>
              <span className={styles.emailNumInput}>email</span>
              <div className={styles.updataInput}>
                <input
                  className={styles.notInput}
                  type="text"
                  value={email}
                  disabled
                />
              </div>
            </div>
            <div>
              <span className={styles.emailNumInput}>휴대폰 번호</span>
              <div className={`${styles.updataInput} ${styles.phoneNum}`}>
                <input
                  name="phone"
                  type="text"
                  value={phone}
                  onChange={onChange}
                />
              </div>
              <div className={styles.message}>
                <div
                  className={valid.phone ? styles.successMsg : styles.errorsMsg}
                >
                  {errors.phone}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className={styles.updata}>
          <button
            id={styles.cancel}
            className={styles.btn}
            onClick={() => setInfoUpdata(false)}
          >
            취소
          </button>
          <button
            type="submit"
            disabled={valid.phone}
            className={styles.btn}
            onClick={onSubmit}
          >
            저장
          </button>
        </div>
      </div>
    </>
  );
};

export default ProfileUpdata;
