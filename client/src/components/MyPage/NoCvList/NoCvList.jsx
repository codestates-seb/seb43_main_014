import React from 'react';
import styles from './noCvList.module.css';

const NoCvList = () => {
  return (
    <div className={styles.noCvList}>
      <div className={styles.cvCard}>
        <div>현재 작성한 이력서 0개</div>
      </div>
      <div className={styles.noNum}>1</div>
    </div>
  );
};

export default NoCvList;
