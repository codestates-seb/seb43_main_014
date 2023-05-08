import React from 'react';
import styles from './profileCard.module.css';

const ProfileCard = ({ setInfoUpdata }) => {
  return (
    <>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img
              className={styles.pic}
              src="https://mediaim.expedia.com/localexpert/1391601/fe50a3dc-b95f-4815-a331-05cbbc16d855.jpg?impolicy=resizecrop&rw=1005&rh=565"
            />
          </div>
          <div className={styles.proInfo}>
            <span className={styles.info}>김도현</span>
            <span className={styles.email}>kdohyn98@github.com</span>
            <span className={styles.num}>010-0000-0000</span>
          </div>
        </div>
        <div className={styles.updata}>
          <button
            className={styles.updataBtn}
            onClick={() => setInfoUpdata(true)}
          >
            수정
          </button>
        </div>
      </div>
    </>
  );
};

export default ProfileCard;
