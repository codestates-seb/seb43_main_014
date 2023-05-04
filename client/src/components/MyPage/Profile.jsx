import React from 'react';
import styles from './profile.module.css';

const Profile = () => {
  const skills = ['JavaScript', 'React', 'Redux'];

  return (
    <div className={styles.container}>
      <div className={styles.proCard}>
        <div className={styles.userInfo}>
          <div className={styles.profilePic}>
            <img
              className={styles.pic}
              src="https://mediaim.expedia.com/localexpert/1391601/fe50a3dc-b95f-4815-a331-05cbbc16d855.jpg?impolicy=resizecrop&rw=1005&rh=565"
            />
          </div>
          <div className={styles.proInfo}>
            <div className={styles.infos}>
              <span className={styles.info}>이름 : </span>
              <span>김도현</span>
            </div>
            <div className={styles.infos}>
              <span className={styles.info}>email : </span>
              <span>kdohyn98@github.com</span>
            </div>
          </div>
        </div>
        <div className={styles.skillTags}>
          {skills.map((el, index) => {
            return (
              <span className={styles.skillTag} key={index}>
                {el}{' '}
              </span>
            );
          })}
        </div>
      </div>
      <div className={styles.infoUpdate}>
        <span>비밀번호 변경</span>
        <span> / </span>
        <span>수정하기</span>
      </div>
    </div>
  );
};

export default Profile;
