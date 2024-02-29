import React from 'react';
import styles from './Button.module.css';

export default function Button({ allTrue, text, onClick }) {
  return (
    <button disabled={allTrue} className={styles.button} onClick={onClick}>
      {text}
    </button>
  );
}
