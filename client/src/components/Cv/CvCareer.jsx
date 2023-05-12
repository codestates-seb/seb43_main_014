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
]; //포문으로 해결
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

const CvCareerInfo = () => {
  const [eduStartMonth, setEduStartMonth] = useState('');
  const [eduStartYear, setEduStartYear] = useState('');
  const [eduEndMonth, setEduEndMonth] = useState('');
  const [eduEndYear, setEduEndYear] = useState('');
  const [comStartMonth, setComStartMonth] = useState('');
  const [comStartYear, setComStartYear] = useState('');
  const [comEndMonth, setComEndMonth] = useState('');
  const [comEndYear, setComEndYear] = useState('');
  const [proStartMonth, setProStartMonth] = useState('');
  const [proStartYear, setProStartYear] = useState('');
  const [proEndMonth, setProEndMonth] = useState('');
  const [proEndYear, setProEndYear] = useState('');

  const onChange = (event) => {
    const {
      target: { name, value },
    } = event;
    if (name === 'edu-start-month') {
      setEduStartMonth(value);
    } else if (name === 'edu-start-year') {
      setEduStartYear(value);
    } else if (name === 'edu-end-month') {
      setEduEndMonth(value);
    } else if (name === 'edu-end-year') {
      setEduEndYear(value);
    } else if (name === 'com-start-month') {
      setComStartMonth(value);
    } else if (name === 'com-start-year') {
      setComStartYear(value);
    } else if (name === 'com-end-month') {
      setComEndMonth(value);
    } else if (name === 'com-end-year') {
      setComEndYear(value);
    } else if (name === 'pro-start-month') {
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
        <div>
          <span>경력 및 프로젝트</span>
          <hr></hr>
        </div>
        <div className="test2">
          <div className="name1">
            <span>학위</span>
            <input></input>
          </div>

          <div className="name2">
            <span>전공</span>
            <input></input>
          </div>
        </div>
        <div className="school">
          <span>학교</span>
          <input></input>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="edu-start-month"
                value={eduStartMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="edu-start-year"
                value={eduStartYear}
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
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="edu-end-month"
                value={eduEndMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="edu-end-year"
                value={eduEndYear}
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
          <StyledButton>+ 학력 추가하기</StyledButton>
        </div>
      </div>
      <div className="body">
        <div className="test2">
          <div className="name1">
            <span>회사명</span>
            <input></input>
          </div>

          <div className="name2">
            <span>직책</span>
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
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="com-start-month"
                value={comStartMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="com-start-year"
                value={comStartYear}
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
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="com-end-month"
                value={comEndMonth}
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
            <FormControl sx={{ m: 1, width: 180 }}>
              <InputLabel id="demo-simple-select-label">년</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="com-end-year"
                value={comEndYear}
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
          <StyledButton>+ 경력 추가하기</StyledButton>
        </div>
      </div>
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
                value={proStartMonth}
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
                value={proStartYear}
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
                value={proEndMonth}
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
                value={proEndYear}
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

export default CvCareerInfo;

const InputContainer = styled.div`
  margin: 0rem 4rem 8rem 4rem;
  display: flex;
  flex-direction: column;
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
    color: gray;
  }

  .name1 {
    display: flex;
    flex-direction: column;
    width: 50%;
    input {
      width: 24rem;
    }
  }
  .name2 {
    display: flex;
    flex-direction: column;
    width: 50%;
    input {
      width: 24.5rem;
    }
  }
  .test2 {
    display: flex;
  }

  .body {
    margin: 3rem;
  }
  .birth2 {
    width: 32.2rem;
  }
  .develop {
    width: 16.8rem;
  }

  .intro {
    input {
      width: 100%;
      height: 10rem;
    }
  }
  .port {
    width: 20rem;
  }
  .school {
    input {
      width: 100%;
    }
  }
`;

const StyledButton = styled.button`
  width: 100%;
  height: 3.5rem;
  font-size: 0.8rem;
  font-weight: bold;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: var(--puple100);
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  &:hover {
    background-color: var(--puple300);
  }
`;

const StyledDeleteButton = styled.button`
  width: 6rem;
  height: 2rem;
  font-size: 0.8rem;
  font-weight: bold;
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
