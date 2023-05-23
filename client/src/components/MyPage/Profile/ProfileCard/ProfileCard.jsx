import React from 'react';
import styles from './profileCard.module.css';

const ProfileCard = ({ userData, setInfoUpdata }) => {
  const { name, email, phone, profileImage } = userData;

  return (
    <>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img
              className={styles.pic}
              src={
                profileImage
                  ? profileImage
                  : 'https://wallpapers-clan.com/wp-content/uploads/2022/08/default-pfp-18.jpg'
              }
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
