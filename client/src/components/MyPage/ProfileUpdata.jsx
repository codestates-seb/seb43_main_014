import React from 'react';

const ProfileUpdata = () => {
  return (
    <>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img
              className={styles.pic}
              src="https://mediaim.expedia.com/localexpert/1391601/fe50a3dc-b95f-4815-a331-05cbbc16d855.jpg?impolicy=resizecrop&rw=1005&rh=565"
            />
            <div>+</div>
          </div>
          <div className={styles.proInfo}>
            <div className={styles.info}>
              <span>이름 *</span>
              <input></input>
            </div>
            <span className={styles.emailNum}>kdohyn98@github.com</span>
            <span>010-0000-0000</span>
          </div>
        </div>
        <div className={styles.updata}>
          <button
            className={styles.updataBtn}
            onClick={() => setInfoUpdata(false)}
          >
            취소
          </button>
          <button
            className={styles.updataBtn}
            onClick={() => setInfoUpdata(false)}
          >
            저장
          </button>
        </div>
      </div>
    </>
  );
};

export default ProfileUpdata;
