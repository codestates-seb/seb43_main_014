import React, { useEffect, useRef, useState } from 'react';
import styles from './profileUpdata.module.css';
import axios from 'axios';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import { useRecoilState } from 'recoil';
import { userState } from '../../../../recoil/AuthAtom';
import AWS from 'aws-sdk';

const ProfileUpdata = ({ setInfoUpdata, userData, setUserData }) => {
  const { name, email, phone, profileImage } = userData;
  const [inputs, setInputs] = useState({
    name: name,
    email: email,
    phone: phone,
  });
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const { userId } = userInfo;
  const token = localStorage.getItem('jwt_token');
  const [errors, setErrors] = useState({
    phone: '',
  });
  const [valid, setValid] = useState({
    phone: false,
  });
  const [isEdit, setIsEdit] = useState(false);
  const [newImg, setNewImg] = useState('');
  const handleSubmit = () => {
    axios
      .patch(
        `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/mypage/${userId}`,
        { name: inputs.name, phone: inputs.phone },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((res) => {
        const getData = JSON.parse(localStorage.getItem('user_info'));
        getData.name = res.data.name;
        localStorage.setItem('user_info', JSON.stringify(getData));
        setUserInfo({ ...userInfo, name: res.data.name });
        setUserData(res.data);
        setInfoUpdata(false);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const validate = (inputs) => {
    const newErrors = {
      phone: '',
    };
    if (inputs.phone.trim() === '') {
      newErrors.phone = '휴대폰 번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else if (!/^010-\d{4}-\d{4}$/.test(inputs.phone)) {
      newErrors.phone =
        "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.";
      setValid((prevValid) => ({ ...prevValid, phone: false }));
    } else {
      newErrors.phone = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, phone: true }));
    }
    setErrors(newErrors);
  };

  const onChange = (e) => {
    const { value, name } = e.target;
    setInputs({
      ...inputs, // 기존의 input 객체를 복사한 뒤
      [name]: value, // name 키를 가진 값을 value 로 설정
    });
    validate({ ...inputs, [name]: value });
    console.log({ ...inputs, [name]: value });
  };

  const onFileChange = (e) => {
    const ACCESS_KEY = process.env.REACT_APP_AWS_ACCESS_KEY;
    const SECRET_ACCESS_KEY = process.env.REACT_APP_AWS_SECRET_ACCESS_KEY;
    const REGION = 'ap-northeast-2';
    const S3_BUCKET = 'hyun-upload-img';
    // AWS ACCESS KEY를 세팅합니다.
    AWS.config.update({
      accessKeyId: ACCESS_KEY,
      secretAccessKey: SECRET_ACCESS_KEY,
    });
    // 버킷에 맞는 이름과 리전을 설정합니다.
    const myBucket = new AWS.S3({
      params: { Bucket: S3_BUCKET },
      region: REGION,
    });
    console.log(process.env.REACT_APP_AWS_ACCESS_KEY);
    const file = e.target.files[0];

    const params = {
      ACL: 'public-read',
      Body: file,
      Bucket: S3_BUCKET,
      Key: file.name,
    };
    const object_name = params.Key;
    console.log(object_name);
    if (!e.target.files.length) {
      return;
    } else {
      myBucket
        .putObject(params)
        .on('httpUploadProgress', (e) => {
          console.log(e);
          setNewImg(
            `https://${S3_BUCKET}.s3.${REGION}.amazonaws.com/${object_name}`,
          );
        })
        .send((err) => {
          if (err) console.log(err);
        });
    }
  };

  useEffect(() => {
    axios
      .post(
        `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/my-page/${userId}/profile-image`,
        {
          profileImage: newImg,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((response) => console.log(response.data))
      .catch((error) => {
        console.log(error);
      });
  }, [newImg]);

  const fileInputRef = useRef(null);

  const onAddClick = () => {
    fileInputRef.current.click();
  };

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
                  : newImg
                  ? newImg
                  : 'https://wallpapers-clan.com/wp-content/uploads/2022/08/default-pfp-18.jpg'
              }
              alt="profileImg"
            />

            <div>
              <Fab
                size="small"
                color="primary"
                aria-label="add"
                onClick={onAddClick}
              >
                <AddIcon />
              </Fab>
            </div>
            <form>
              <input
                type="file"
                accept="image/*"
                onChange={onFileChange}
                ref={fileInputRef}
                className={styles.imgAdd}
              />
            </form>
          </div>
          <div className={styles.proInfo}>
            <div>
              <span className={styles.info}>이름</span>
              <div className={styles.updataInput}>
                {isEdit ? (
                  <input
                    name="name"
                    type="text"
                    value={inputs.name}
                    onChange={onChange}
                  />
                ) : (
                  <span
                    className={styles.uInfo}
                    onClick={() => setIsEdit(true)}
                  >
                    {name}
                  </span>
                )}
              </div>
            </div>
            <div>
              <span className={styles.info}>email</span>
              <div className={styles.updataInput}>
                <span className={styles.notInput}>{email}</span>
              </div>
            </div>
            <div>
              <span className={styles.info}>휴대폰 번호</span>
              <div className={`${styles.updataInput} ${styles.phoneNum}`}>
                {isEdit ? (
                  <input
                    name="phone"
                    type="text"
                    value={inputs.phone}
                    onChange={onChange}
                  />
                ) : (
                  <span
                    className={styles.uInfo}
                    onClick={() => setIsEdit(true)}
                  >
                    {phone}
                  </span>
                )}
              </div>
              <div className={styles.message}>
                <div
                  className={valid.phone ? styles.successMsg : styles.errorsMsg}
                >
                  {errors.phone}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className={styles.updata}>
          <button
            id={styles.cancel}
            className={styles.btn}
            onClick={() => setInfoUpdata(false)}
          >
            취소
          </button>
          {valid.phone ? (
            <button type="submit" className={styles.btn} onClick={handleSubmit}>
              저장
            </button>
          ) : (
            <button disabled className={styles.notBtn}>
              저장
            </button>
          )}
        </div>
      </div>
    </>
  );
};

export default ProfileUpdata;
