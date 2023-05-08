import React from 'react';
import styles from './Button.module.css';

export default function Button({ allTrue, text }) {
  return (
    <button disabled={allTrue} className={styles.button}>
      {text}
    </button>
  );
}
