import React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import TagInput from './TagInput';
import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { CvContentAtom } from '../../recoil/CvContentAtom';
import { useRecoilState } from 'recoil';

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

const CvCareerInfo = ({ setCheck }) => {
  const [degree, setDegree] = useState('');
  const [major, setMajor] = useState('');
  const [schoolName, setSchoolName] = useState('');
  const [admissionMonth, setAdmissionMonth] = useState('');
  const [admissionYear, setAdmissionYear] = useState('');
  const [graduationMonth, setGraduationMonth] = useState('');
  const [graduationYear, setGraduationYear] = useState('');
  const [eduDescription, setEduDescription] = useState('');

  const [companyName, setCompanyName] = useState('');
  const [duty, setDuty] = useState('');
  const [developmentJob, setDevelopmentJob] = useState('');
  const [joinMonth, setJoinMonth] = useState('');
  const [joinYear, setJoinYear] = useState('');
  const [retirementMonth, setRetirementMonth] = useState('');
  const [retirementYear, setRetirementYear] = useState('');
  const [jobDescription, setJobDescription] = useState('');

  const [projectSubject, setProjectSubject] = useState('');
  const [part, setPart] = useState('');
  const [link, setLink] = useState('');
  const [startMonth, setStartMonth] = useState('');
  const [startYear, setStartYear] = useState('');
  const [endMonth, setEndMonth] = useState('');
  const [endYear, setEndYear] = useState('');
  const [proDescription, setProDescription] = useState('');
  const [cvContent, setCvContent] = useRecoilState(CvContentAtom);

  const [tags, setTags] = useState('');

  const cvContent2 = {
    educations: [
      {
        degree: degree,
        major: major,
        schoolName: schoolName,
        admissionMonth: admissionMonth,
        admissionYear: admissionYear,
        graduationMonth: graduationMonth,
        graduationYear: graduationYear,
        description: eduDescription,
      },
    ],

    careers: [
      {
        companyName: companyName,
        duty: duty,
        developmentJob: developmentJob,
        joinMonth: joinMonth,
        joinYear: joinYear,
        retirementMonth: retirementMonth,
        retirementYear: retirementYear,
        description: jobDescription,
      },
    ],

    projects: [
      {
        projectSubject: projectSubject,
        part: part,
        link: link,
        startMonth: startMonth,
        startYear: startYear,
        endMonth: endMonth,
        endYear: endYear,
        // projectSkillStacks: [
        //   {
        //     skillStackId: tags,
        //   },
        // ],
        description: proDescription,
      },
    ],
  };

  const handleClickSave = () => {
    setCvContent((prev) => ({ ...prev, ...cvContent2 }));
    alert('임시저장이 완료되었습니다.');
    setCheck(true);
  };

  useEffect(() => {
    localStorage.setItem('CvBasicContent', JSON.stringify({ ...cvContent }));
  }, [cvContent]);

  useEffect(() => {
    const CvBasicContent = localStorage.getItem('CvBasicContent');
    const { educations, careers, projects } = JSON.parse(CvBasicContent);
    setDegree(educations[0].degree);
    setMajor(educations[0].major);
    setSchoolName(educations[0].schoolName);
    setAdmissionMonth(educations[0].admissionMonth);
    setAdmissionYear(educations[0].admissionYear);
    setGraduationMonth(educations[0].graduationMonth);
    setGraduationYear(educations[0].graduationYear);
    setEduDescription(educations[0].eduDescription);
    setCompanyName(careers[0].companyName);
    setDuty(careers[0].duty);
    setDevelopmentJob(careers[0].developmentJob);
    setJoinMonth(careers[0].joinMonth);
    setJoinYear(careers[0].joinYear);
    setRetirementMonth(careers[0].retirementMonth);
    setRetirementYear(careers[0].retirementYear);
    setJobDescription(careers[0].jobDescription);
    setProjectSubject(projects[0].projectSubject);
    setPart(projects[0].part);
    setLink(projects[0].link);
    setStartMonth(projects[0].startMonth);
    setStartYear(projects[0].startYear);
    setEndMonth(projects[0].endMonth);
    setEndYear(projects[0].endYear);
    setProDescription(projects[0].proDescription);
  }, []);

  const onChange = (event) => {
    const {
      target: { name, value },
    } = event;
    if (name === 'admissionmonth') {
      setAdmissionMonth(value);
    } else if (name === 'admissionyear') {
      setAdmissionYear(value);
    } else if (name === 'graduationmonth') {
      setGraduationMonth(value);
    } else if (name === 'graduationyear') {
      setGraduationYear(value);
    } else if (name === 'joinmonth') {
      setJoinMonth(value);
    } else if (name === 'joinyear') {
      setJoinYear(value);
    } else if (name === 'retirementmonth') {
      setRetirementMonth(value);
    } else if (name === 'retirementyear') {
      setRetirementYear(value);
    } else if (name === 'startmonth') {
      setStartMonth(value);
    } else if (name === 'startyear') {
      setStartYear(value);
    } else if (name === 'endmonth') {
      setEndMonth(value);
    } else if (name === 'endyear') {
      setEndYear(value);
    } else if (name === 'degree') {
      setDegree(value);
    } else if (name === 'major') {
      setMajor(value);
    } else if (name === 'schoolname') {
      setSchoolName(value);
    } else if (name === 'edudescription') {
      setEduDescription(value);
    } else if (name === 'companyname') {
      setCompanyName(value);
    } else if (name === 'duty') {
      setDuty(value);
    } else if (name === 'developmentjob') {
      setDevelopmentJob(value);
    } else if (name === 'jobdescription') {
      setJobDescription(value);
    } else if (name === 'projectsubject') {
      setProjectSubject(value);
    } else if (name === 'part') {
      setPart(value);
    } else if (name === 'protags') {
      setTags(value);
    } else if (name === 'prodescription') {
      setProDescription(value);
    } else if (name === 'link') {
      setLink(value);
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
            <input
              maxLength={30}
              name="degree"
              type="text"
              value={degree}
              onChange={onChange}
            ></input>
          </div>

          <div className="name2">
            <span>전공</span>
            <input
              maxLength={30}
              name="major"
              type="text"
              value={major}
              onChange={onChange}
            ></input>
          </div>
        </div>
        <div className="school">
          <span>학교</span>
          <input
            maxLength={30}
            name="schoolname"
            type="text"
            value={schoolName}
            onChange={onChange}
          ></input>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="admissionmonth"
                value={admissionMonth}
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
                name="admissionyear"
                value={admissionYear}
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
                name="graduationmonth"
                value={graduationMonth}
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
                name="graduationyear"
                value={graduationYear}
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
          <textarea
            name="edudescription"
            type="text"
            value={eduDescription}
            onChange={onChange}
          ></textarea>
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
            <input
              maxLength={30}
              name="companyname"
              type="text"
              value={companyName}
              onChange={onChange}
            ></input>
          </div>

          <div className="name2">
            <span>직책</span>
            <input
              maxLength={30}
              name="duty"
              type="text"
              value={duty}
              onChange={onChange}
            ></input>
          </div>
        </div>
        <div className="school">
          <span>개발직무</span>
          <input
            maxLength={30}
            name="developmentjob"
            type="text"
            value={developmentJob}
            onChange={onChange}
          ></input>
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 135, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="joinmonth"
                value={joinMonth}
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
                name="joinyear"
                value={joinYear}
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
                name="retirementmonth"
                value={retirementMonth}
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
                name="retirementyear"
                value={retirementYear}
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
          <textarea
            name="jobdescription"
            type="text"
            value={jobDescription}
            onChange={onChange}
          ></textarea>
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
            <input
              maxLength={30}
              name="projectsubject"
              type="text"
              value={projectSubject}
              onChange={onChange}
            ></input>
          </div>

          <div className="name2">
            <span>역할</span>
            <input
              maxLength={30}
              name="part"
              type="text"
              value={part}
              onChange={onChange}
            ></input>
          </div>
        </div>
        <div className="school">
          <span>프로젝트 링크</span>
          <input
            name="link"
            type="text"
            value={link}
            onChange={onChange}
          ></input>
          <span>기술스택</span>
          <TagInput tags={tags} setTags={setTags} />
        </div>
        <div className="test2">
          <div className="birth2">
            <span>시작일</span>
            <FormControl sx={{ m: 1, width: 115, height: 0 }}>
              <InputLabel id="demo-simple-select-label">월</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                name="startmonth"
                value={startMonth}
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
                name="startyear"
                value={startYear}
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
                name="endmonth"
                value={endMonth}
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
                name="endyear"
                value={endYear}
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
          <textarea
            name="prodescription"
            type="text"
            value={proDescription}
            onChange={onChange}
          ></textarea>
        </div>
        <div>
          <StyledDeleteButton>삭제하기</StyledDeleteButton>
        </div>
        <div>
          <StyledButton>+ 프로젝트 추가하기</StyledButton>
        </div>
        <StyledButton onClick={handleClickSave}>임시저장</StyledButton>
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
  textarea {
    width: 100%;
    height: 20rem;
    border-radius: 0.2rem;
    border: 1px solid #c8c8c8;
    :hover {
      border: 1px solid black;
    }
  }
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
