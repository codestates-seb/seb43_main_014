import React from 'react';
import styles from './profileCard.module.css';

const ProfileCard = ({ userData, setInfoUpdata }) => {
  const { name, email, phone, profileImage } = userData;

  return (
    <>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img className={styles.pic} src={profileImage} alt="profileImg" />
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
