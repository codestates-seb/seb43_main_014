import React, { useState } from 'react';
import styles from './profile.module.css';
import ProfileUpdata from './ProfileUpdata/ProfileUpdata';
import ProfileCard from './ProfileCard/ProfileCard';

const Profile = () => {
  const skills = ['JavaScript', 'React', 'Redux'];
  const [infoUpdata, setInfoUpdata] = useState(false);

  return (
    <div>
      {infoUpdata ? (
        <ProfileUpdata setInfoUpdata={setInfoUpdata} />
      ) : (
        <ProfileCard setInfoUpdata={setInfoUpdata} />
      )}
    </div>
  );
};
export default Profile;
{
  /* <div className={styles.skillTags}>
          {skills.map((el, index) => {
            return (
              <span className={styles.skillTag} key={index}>
                {el}{' '}
              </span>
            );
          })}
        </div> */
}
