import React, { useState } from 'react';
import styles from './profileUpdata.module.css';
import axios from 'axios';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import Btn from '../../Btn/Btn';

const ProfileUpdata = ({ setInfoUpdata }) => {
  const name = '도현';
  const email = `kdohyn98@github.com`;
  const pNum = '010-1234-1234';
  const [isEdit, setIsEdit] = useState(false);
  const [userName, setUserName] = useState(name);

  const onSubmit = () => {
    axios.patch(``, {});
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
              {isEdit ? (
                <div className={styles.updataInput}>
                  <form
                    onSubmit={(e) => {
                      e.preventDefault();
                      onSubmit();
                      setIsEdit(false);
                    }}
                  >
                    <input
                      type="text"
                      defaultValue={userName}
                      onChange={(e) => setUserName(e.target.value)}
                    />
                  </form>
                </div>
              ) : (
                <div className={styles.updataInput}>
                  <form
                    onClick={() => {
                      setIsEdit(true);
                    }}
                  >
                    <input type="text" defaultValue={userName} />
                  </form>
                </div>
              )}
            </div>
            <div>
              <span className={styles.emailNumInput}>email</span>
              <div className={styles.updataInput}>
                <form>
                  <input
                    className={styles.notInput}
                    type="text"
                    value={email}
                    disabled
                  />
                </form>
              </div>
            </div>
            <div>
              <span className={styles.emailNumInput}>휴대폰 번호</span>
              <div className={styles.updataInput}>
                <form>
                  <input
                    className={styles.notInput}
                    disabled
                    type="text"
                    value={pNum}
                  />
                </form>
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
          <button className={styles.btn} onClick={() => setInfoUpdata(false)}>
            저장
          </button>
          {/* <Btn btnName="저장" onClick={() => setInfoUpdata(false)} /> */}
        </div>
      </div>
    </>
  );
};

export default ProfileUpdata;
