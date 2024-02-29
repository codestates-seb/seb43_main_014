import React from 'react';
import styles from './btn.module.css';

const Btn = (props) => {
  return <button className={styles.btn}>{props.btnName}</button>;
};

export default Btn;
