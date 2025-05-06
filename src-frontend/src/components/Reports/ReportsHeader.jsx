import PropTypes from 'prop-types';
import { Box, Typography } from "@mui/material";

const ReportsHeader = ({ title, subtitle }) => {
  return (
    <Box className="reports-header">
      <Box className="reports-header__content">
        <Typography variant="h4" className="reports-header__title">
          {title}
        </Typography>
        <Typography variant="subtitle1" className="reports-header__subtitle">
          {subtitle}
        </Typography>
      </Box>
    </Box>
  );
};

ReportsHeader.propTypes = {
  title: PropTypes.string.isRequired,
  subtitle: PropTypes.string.isRequired,
};

export default ReportsHeader;
