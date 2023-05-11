import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

export default function CvCustomInput() {
  return (
    <div>
      <Accordion sx={{ width: '100%' }}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
          <Typography>사용자 정의 섹션</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>사용자 정의 세션</Typography>
        </AccordionDetails>
      </Accordion>
    </div>
  );
}
