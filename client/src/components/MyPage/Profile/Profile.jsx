import React, { useState } from 'react';
import styles from './profile.module.css';
import ProfileUpdata from './ProfileUpdata/ProfileUpdata';
import ProfileCard from './ProfileCard/ProfileCard';

const Profile = () => {
  const name = '도현';
  const email = `kdohyn98@github.com`;
  const phone = '01012341234';
  const [infoUpdata, setInfoUpdata] = useState(false);
  // const [userName, setUserName] = useState(name);
  const [inputs, setInputs] = useState({
    name: name,
    email: email,
    phone: phone,
  });

  return (
    <div>
      {infoUpdata ? (
        <ProfileUpdata
          inputs={inputs}
          setInputs={setInputs}
          setInfoUpdata={setInfoUpdata}
        />
      ) : (
        <ProfileCard
          inputs={inputs}
          setInfoUpdata={setInfoUpdata}
          // onChange={onChange}
        />
      )}
    </div>
  );
};
export default Profile;
