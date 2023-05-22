import React from 'react';
import styles from './pagination.module.css';
const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const handlePageChange = (page) => {
    if (page >= 1 && page <= totalPages) {
      onPageChange(page);
    }
  };

  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(
        <li
          key={i}
          onClick={() => handlePageChange(i)}
          className={currentPage === i ? 'active' : ''}
        >
          {i}
        </li>,
      );
    }
    return pageNumbers;
  };

  return (
    <ul className="pagination">
      <li onClick={() => handlePageChange(currentPage - 1)}>&laquo;</li>
      {renderPageNumbers()}
      <li onClick={() => handlePageChange(currentPage + 1)}>&raquo;</li>
    </ul>
  );
};

export default Pagination;
