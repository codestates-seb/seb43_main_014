import React from 'react';
import styles from './cvList.module.css';

const CvList = () => {
  return (
    <div className={styles.container}>
      <div className={styles.cvContainer}>
        <div className={styles.cvCard}>
          <div>
            <h4>이력서 Title</h4>
            <div className={styles.cvJob}>개발 직무</div>
          </div>
          <div className={styles.cvDate}>2023.05.08</div>
        </div>
        <div className={styles.cvCard}>
          <div>
            <h4>이력서 Title</h4>
            <div>개발 직무</div>
          </div>
          <div className={styles.cvDate}>작성 날자</div>
        </div>
        <div className={styles.cvCard}>
          <div>
            <h4>이력서 Title</h4>
            <div>개발 직무</div>
          </div>
          <div className={styles.cvDate}>작성 날자</div>
        </div>
      </div>
      <div className={styles.pageNum}>1 2 3 4</div>
    </div>
  );
};

export default CvList;
