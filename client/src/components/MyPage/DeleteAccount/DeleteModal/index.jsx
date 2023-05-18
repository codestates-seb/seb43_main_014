import { React, useState } from 'react';
import styles from './deleteModal.module.css';
import axios from 'axios';
import Modal from '../../../common/Modal';
import { useRecoilState } from 'recoil';
import { isLoginState, userState } from '../../../../recoil/AuthAtom';
import { useNavigate } from 'react-router-dom';
// role: 'dialog',

const DeleteModal = ({ openModalHandler }) => {
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [check, setCheck] = useState(false);
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const token = localStorage.getItem('jwt_token');
  const navigate = useNavigate();
  const onDelete = () => {
    axios
      .delete(
        `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/mypage/${userInfo.userId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((res) => {
        console.log('res', res);
        localStorage.removeItem('jwt_token');
        localStorage.removeItem('user_info');
        setIsLogin(false);
        navigate('/');
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onCheck = () => {
    setCheck(!check);
  };

  return (
    <>
      <Modal openModalHandler={openModalHandler}>
        <div>
          <h4>계정 삭제</h4>
          <div className={styles.modalGuide}>
            {userInfo.name} 님({userInfo.email})의 계정 삭제를 선택 하였습니다.
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
              계정을 삭제하면 되돌릴 수 없으며, 삭제한 데이터를 복구할 수 없음을
              이해했습니다.
            </span>
          </div>
          <div className={styles.modalGuideBtn}>
            {check ? (
              <button
                className={styles.deleteBtn}
                type="submit"
                onClick={onDelete}
              >
                계정 삭제하기
              </button>
            ) : (
              <button className={styles.notBtn}>계정 삭제하기</button>
            )}
          </div>
        </div>
      </Modal>
    </>
  );
};

export default DeleteModal;
