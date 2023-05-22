import React, { useState } from 'react';
import styles from './cvList.module.css';
import { Pagination } from '@mui/material';

const CvList = ({ cv, cvs }) => {
  const { title, developmentJob, createdAt } = cv;
  const { totalPages, totalElements } = cvs;
  const [currentPage, setCurrentPage] = useState(1);
  const handlePageChange = (page) => {
    setCurrentPage(page);
    // 페이지가 변경될 때 해당 페이지의 데이터를 가져오거나 업데이트하는 로직을 작성해야 합니다.
  };
  return (
    <div className={styles.container}>
      <div className={styles.cvContainer}>
        <div className={styles.cvCard}>
          <div>
            <h4>{title}</h4>
            <div className={styles.cvJob}>{developmentJob}</div>
          </div>
          <div className={styles.cvDate}>{createdAt}</div>
        </div>
      </div>
      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </div>
  );
};

export default CvList;
