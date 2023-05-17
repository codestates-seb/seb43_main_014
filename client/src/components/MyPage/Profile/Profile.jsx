import React, { useState } from 'react';
import ProfileUpdata from './ProfileUpdata/ProfileUpdata';
import ProfileCard from './ProfileCard/ProfileCard';

const Profile = ({ userData, setUserData }) => {
  const [infoUpdata, setInfoUpdata] = useState(false);

  return (
    <div>
      {infoUpdata ? (
        <ProfileUpdata
          setInfoUpdata={setInfoUpdata}
          userData={userData}
          setUserData={setUserData}
        />
      ) : (
        <ProfileCard setInfoUpdata={setInfoUpdata} userData={userData} />
      )}
    </div>
  );
};
export default Profile;
