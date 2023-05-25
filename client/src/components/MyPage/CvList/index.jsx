import React from 'react';
import styles from './cvList.module.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const CvList = ({ cv, pageData, setPageData }) => {
  const { title, developmentJob, createdAt, cvId } = cv;
  const token = localStorage.getItem('jwt_token');
  const navigate = useNavigate();
  const onCvList = () => {
    const getData = JSON.parse(localStorage.getItem('user_info'));
    getData.cvId = cvId;
    localStorage.setItem('user_info', JSON.stringify(getData));
    navigate('/cv');
  };
  const onCvEdit = () => {
    const getData = JSON.parse(localStorage.getItem('user_info'));
    getData.cvId = cvId;
    localStorage.setItem('user_info', JSON.stringify(getData));
    navigate('/cv/edit');
  };
  const onCvDelete = () => {
    axios
      .delete(
        `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/cv/${cvId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((res) => {
        console.log('res', res);
        // const newList = pageData.latestCvs.filter((el) => cvId !== el.cvId);
        // setPageData((prev) => ({ ...prev, latestCvs: newList }));
        navigate(0);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className={styles.cvCard}>
      <div className={styles.card}>
        <div>
          <h4 onClick={onCvList}>{title}</h4>
          <div className={styles.cvJob}>{developmentJob}</div>
        </div>
        <div className={styles.cardRight}>
          <button onClick={onCvEdit}>수정</button>
          <span>/</span>
          <button onClick={onCvDelete}>삭제</button>
        </div>
      </div>
      <div className={styles.cvDate}>{createdAt}</div>
    </div>
  );
};

export default CvList;
