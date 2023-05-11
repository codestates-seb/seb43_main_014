import React, { useState } from 'react';
import styles from './profile.module.css';
import ProfileUpdata from './ProfileUpdata/ProfileUpdata';
import ProfileCard from './ProfileCard/ProfileCard';

const Profile = ({ inputs, setInputs }) => {
  const [infoUpdata, setInfoUpdata] = useState(false);

  return (
    <div>
      {infoUpdata ? (
        <ProfileUpdata
          inputs={inputs}
          setInputs={setInputs}
          setInfoUpdata={setInfoUpdata}
        />
      ) : (
        <ProfileCard inputs={inputs} setInfoUpdata={setInfoUpdata} />
      )}
    </div>
  );
};
export default Profile;
