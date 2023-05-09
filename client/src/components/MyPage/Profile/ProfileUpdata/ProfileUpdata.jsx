import React, { useState } from 'react';
import styles from './profileUpdata.module.css';
import axios from 'axios';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';

const ProfileUpdata = ({ setInfoUpdata }) => {
  const name = '도현';
  const email = `kdohyn98@github.com`;
  const pNum = '010-1234-1234';
  const [userName, setUserName] = useState(name);
  const [userPNum, setUserPNum] = useState(pNum);

  const onSubmit = () => {
    axios.patch(``, {});
    console.log('asd');
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
                  type="text"
                  defaultValue={userName}
                  onChange={(e) => setUserName(e.target.value)}
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
              <div className={styles.updataInput}>
                <input
                  type="text"
                  defaultValue={userPNum}
                  onChange={(e) => setUserPNum(e.target.value)}
                />
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
          <button type="submit" className={styles.btn} onClick={onSubmit}>
            저장
          </button>
        </div>
      </div>
    </>
  );
};

export default ProfileUpdata;
