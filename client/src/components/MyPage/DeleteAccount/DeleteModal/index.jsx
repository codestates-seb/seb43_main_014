import { React, useState } from 'react';
import styles from './deleteModal.module.css';
// role: 'dialog',

const DeleteModal = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [check, setCheck] = useState(false);
  const openModalHandler = () => {
    // TODO : isOpen의 상태를 변경하는 메소드를 구현합니다.
    setIsOpen(!isOpen);
  };
  const onClick = () => {
    console.log('btn');
  };
  const onCheck = () => {
    // ck = e.target.checked;
    // console.log(e.target.checked);
    setCheck(!check);
  };
  return (
    <>
      <div className={styles.modalContainer}>
        <button className={styles.modalBtn} onClick={openModalHandler}>
          삭제하기
        </button>
        {isOpen ? (
          <div className={styles.modalBackdrop} onClick={openModalHandler}>
            <div
              className={styles.modalView}
              onClick={(e) => e.stopPropagation()}
            >
              <div>
                <h4>계정 삭제</h4>
                <div className={styles.modalGuide}>
                  (이름) 님(email)의 계정 삭제를 선택 하였습니다.
                </div>
                <span>프로필 정보</span>
                <div className={styles.modalGuideList}>
                  <ul>
                    <li>
                      고객님의 이름, 이메일, 프로필 사진, 이력서를 포함한 모든
                      개인정보를 삭제합니다.
                    </li>
                    <li>삭제한 정보는 복구할 수 없습니다.</li>
                  </ul>
                </div>
                <div className={styles.modalGuideCheck}>
                  <div className={styles.modalGuideCheckbox}>
                    <input type="checkbox" onClick={onCheck} />
                  </div>
                  <span>
                    계정을 삭제하면 되돌릴 수 없으며, 삭제한 데이터를 복구할 수
                    없음을 이해했습니다.
                  </span>
                </div>
                <div className={styles.modalGuideBtn}>
                  <button disabled={check} onClick={onClick}>
                    계정 삭제하기
                  </button>
                </div>
              </div>
            </div>
          </div>
        ) : null}
      </div>
    </>
  );
};

export default DeleteModal;
