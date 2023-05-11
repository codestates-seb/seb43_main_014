import React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useState } from 'react';
import styled from 'styled-components';

const months = [
  '월',
  '1',
  '2',
  '3',
  '4',
  '5',
  '6',
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
];
const years = [
  '년',
  '2012',
  '2011',
  '2010',
  '2009',
  '2008',
  '2007',
  '2006',
  '2005',
  '2004',
  '2003',
  '2002',
  '2001',
  '2000',
  '1999',
  '1998',
  '1997',
  '1996',
  '1995',
  '1994',
  '1993',
  '1992',
  '1991',
  '1990',
  '1989',
  '1988',
  '1987',
  '1986',
  '1985',
  '1984',
  '1983',
  '1982',
  '1981',
  '1980',
  '1979',
  '1978',
  '1977',
  '1976',
  '1975',
  '1974',
  '1973',
  '1972',
  '1971',
  '1970',
  '1969',
  '1968',
  '1967',
  '1966',
  '1965',
  '1964',
  '1963',
  '1962',
  '1961',
  '1960',
];

const CvProject = () => {
  const [prostartmonth, setProStartMonth] = useState('');
  const [prostartyear, setProStartYear] = useState('');
  const [proendmonth, setProEndMonth] = useState('');
  const [proendyear, setProEndYear] = useState('');

  const onChange = (event) => {
    const {
      target: { name, value },
    } = event;
    if (name === 'pro-start-month') {
      setProStartMonth(value);
    } else if (name === 'pro-start-year') {
      setProStartYear(value);
    } else if (name === 'pro-end-month') {
      setProEndMonth(value);
    } else if (name === 'pro-end-year') {
      setProEndYear(value);
    }
  };

  return (
    <InputContainer>
      <div className="body">
        <div className="test2">
          <div className="name1">
            <span>프로젝트 명</span>
            <input></input>
          </div>

          <div className="name2">
            <span>역할</span>
            <input></input>
          </div>
        </div>
        <div className="school">
          <span>개발직무</span>
          <input></input>
          <span>기술스택</span>
          <input></input>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="pro-start-month"
                value={prostartmonth}
                label="Month"
                onChange={onChange}
              >
                {months.map((month) => (
                  <MenuItem key={month} value={month}>
                    {month}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl sx={{ m: 1, width: 170 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="pro-start-year"
                value={prostartyear}
                label="Year"
                onChange={onChange}
              >
                {years.map((year) => (
                  <MenuItem key={year} value={year}>
                    {year}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
          <div className="birth2">
            <span>종료일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="pro-end-month"
                value={proendmonth}
                label="Month"
                onChange={onChange}
              >
                {months.map((month) => (
                  <MenuItem key={month} value={month}>
                    {month}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <FormControl sx={{ m: 1, width: 170 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="pro-end-year"
                value={proendyear}
                label="Year"
                onChange={onChange}
              >
                {years.map((year) => (
                  <MenuItem key={year} value={year}>
                    {year}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
        </div>

        <div className="intro">
          <span>설명</span>
          <input></input>
        </div>
        <div>
          <StyledDeleteButton>삭제하기</StyledDeleteButton>
        </div>
        <div>
          <StyledButton>+ 프로젝트 추가하기</StyledButton>
        </div>
      </div>
    </InputContainer>
  );
};

export default CvProject;

const InputContainer = styled.div`
  margin: 4rem;
  display: flex;
  flex-direction: column;

  border: 1px solid blue;
  width: 55rem;
  background-color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  input {
    width: 50%;
    height: 2.5rem;
    margin: 0 0 1rem 0rem;
    border-radius: 0.2rem;
    border: 1px solid #c8c8c8;
    :hover {
      border: 1px solid black;
    }
  }
  span {
    font-size: 1rem;
  }

  .name1 {
    display: flex;
    flex-direction: column;
    width: 50%;
    input {
      width: 22rem;
    }
  }
  .name2 {
    display: flex;
    flex-direction: column;
    width: 50%;
    input {
      width: 22rem;
    }

    border: 1px solid black;
  }
  .test2 {
    border: 1px solid red;
    display: flex;
  }

  .body {
    margin: 3rem;
    border: 1px solid skyblue;
  }
  .birth2 {
    width: 32.2rem;
    border: 1px solid red;
  }
  .develop {
    border: 1px solid red;
    width: 16.8rem;
  }
  .tag {
    border: 1px solid green;
  }
  .link {
    border: 1px solid blue;
  }
  .intro {
    input {
      width: 100%;
      height: 10rem;
    }
  }
  .port {
    width: 20rem;
    border: 1px solid blue;
  }
  .school {
    input {
      width: 100%;
    }
    border: 1px solid blue;
  }
`;

const StyledButton = styled.button`
  width: 100%;
  height: 3.5rem;
  font-size: 0, 8rem;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: black;

  &:hover {
    background-color: var(--puple300);
  }
`;

const StyledDeleteButton = styled.button`
  width: 6rem;
  height: 2rem;
  font-size: 0, 8rem;
  cursor: pointer;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: white;
  border: 1px solid gray;
  color: black;

  &:hover {
    background-color: var(--puple300);
  }
`;
