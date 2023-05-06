import React from 'react';
import styles from './FormBox.module.css';

export default function FormBox({ children }) {
  return <section className={styles.right}>{children}</section>;
}
