import React, { useState } from 'react';
import styles from './pwModal.module.css';

const PwModal = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [check, setCheck] = useState(false);
  const openModalHandler = () => {
    // TODO : isOpen의 상태를 변경하는 메소드를 구현합니다.
    setIsOpen(!isOpen);
  };
  const onClick = () => {
    console.log('btn');
  };
  const onCheck = (e, check) => {
    // setCheck(!check);
    check = e.target.checked;
    console.log(check);
  };
  return (
    <>
      <div className={styles.modalContainer}>
        <button className={styles.modalBtn} onClick={openModalHandler}>
          비밀번호 변경
        </button>
        {isOpen ? (
          <div className={styles.modalBackdrop} onClick={openModalHandler}>
            <div
              className={styles.modalView}
              onClick={(e) => e.stopPropagation()}
            >
              <div>
                <h4>비밀번호 변경</h4>
              </div>
              <div>
                <form>
                  <div className={styles.modalChangeInput}>
                    <span>현재 비밀번호</span>
                    <input></input>
                    <div>
                      <span className={styles.inputDetail}>
                        비밀번호를 잊으셨나요?{' '}
                      </span>
                      <span>비밀번호 재설정(로그인페이지로 이동)</span>
                    </div>
                  </div>
                  <div className={styles.modalChangeInput}>
                    <span>비밀번호</span>
                    <input />
                    <span className={styles.inputDetail}>
                      비밀번호 (영문자, 숫자, 특수문자 포함 최소 8~20자)
                    </span>
                  </div>
                  <div className={styles.modalChangeInput}>
                    <span>비밀번호 확인</span>
                    <input></input>
                  </div>
                  <div className={styles.modalChangeBtn}>
                    <button
                      id={styles.cancel}
                      className={styles.btn}
                      onClick={() => openModalHandler()}
                    >
                      취소
                    </button>
                    <button className={styles.btn}>저장</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        ) : null}
      </div>
    </>
  );
};

export default PwModal;
