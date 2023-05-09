import React from 'react';
import styles from './pwModal.module.css';
import Modal from '../../../common/Modal';
import { Link } from 'react-router-dom';

const PwModal = ({ openModalHandler }) => {
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
              <input type="password" />
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
              <input type="password" />
              <span className={styles.inputDetail}>
                비밀번호 (영문자, 숫자, 특수문자 포함 최소 8~20자)
              </span>
            </div>
            <div className={styles.modalChangeInput}>
              <span>비밀번호 확인</span>
              <input type="password" />
            </div>
            <div className={styles.modalChangeBtn}>
              <button
                id={styles.cancel}
                className={styles.btn}
                onClick={openModalHandler}
              >
                취소
              </button>
              <button className={styles.btn}>저장</button>
            </div>
          </form>
        </div>
      </Modal>
    </>
  );
};

export default PwModal;
