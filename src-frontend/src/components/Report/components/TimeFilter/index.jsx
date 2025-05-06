import PropTypes from "prop-types";
import { Box, Typography, Slider, Popover } from "@mui/material";
import "./styles.scss";

const TimeFilter = ({
  open,
  anchorEl,
  timeRange,
  onClose,
  onTimeRangeChange,
  formatTimeLabel,
}) => {
  return (
    <Popover
      open={open}
      anchorEl={anchorEl}
      onClose={onClose}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'center',
      }}
      transformOrigin={{
        vertical: 'top',
        horizontal: 'center',
      }}
    >
      <Box className="report-list__time-popover">
        <Typography variant="subtitle2" gutterBottom>
          Filtrar por hora
        </Typography>
        <Slider
          value={timeRange}
          onChange={onTimeRangeChange}
          valueLabelDisplay="auto"
          valueLabelFormat={formatTimeLabel}
          min={0}
          max={24}
          step={0.5}
          marks={[
            { value: 0, label: '00:00' },
            { value: 6, label: '06:00' },
            { value: 12, label: '12:00' },
            { value: 18, label: '18:00' },
            { value: 24, label: '24:00' }
          ]}
        />
        <Typography variant="caption" color="textSecondary">
          {formatTimeLabel(timeRange[0])} - {formatTimeLabel(timeRange[1])}
        </Typography>
      </Box>
    </Popover>
  );
};

TimeFilter.propTypes = {
  open: PropTypes.bool.isRequired,
  anchorEl: PropTypes.object,
  timeRange: PropTypes.arrayOf(PropTypes.number).isRequired,
  onClose: PropTypes.func.isRequired,
  onTimeRangeChange: PropTypes.func.isRequired,
  formatTimeLabel: PropTypes.func.isRequired,
};

export default TimeFilter;
