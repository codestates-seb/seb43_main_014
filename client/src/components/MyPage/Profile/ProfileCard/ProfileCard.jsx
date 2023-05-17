import React from 'react';
import styles from './profileCard.module.css';

const ProfileCard = ({ userData, setInfoUpdata }) => {
  const { name, email, phone } = userData;

  return (
    <>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img
              className={styles.pic}
              src="https://mediaim.expedia.com/localexpert/1391601/fe50a3dc-b95f-4815-a331-05cbbc16d855.jpg?impolicy=resizecrop&rw=1005&rh=565"
              alt="profileImg"
            />
          </div>
          <div className={styles.proInfo}>
            <span className={styles.info}>{name}</span>
            <span className={styles.email}>{email}</span>
            <span className={styles.num}>{phone}</span>
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
