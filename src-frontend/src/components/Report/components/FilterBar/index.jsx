import PropTypes from "prop-types";
import { Box, Stack, Chip, IconButton } from "@mui/material";
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import ClearIcon from '@mui/icons-material/Clear';
import "./styles.scss";
import { filterConfig } from "../../config/incidentConfig";

const FilterBar = ({
  activeFilter,
  timeRange,
  onFilterClick,
  onClearFilter,
  onTimeFilterClick,
}) => {
  return (
    <Box className="report-list__filters">
      <Stack direction="row" spacing={1} className="report-list__filters-left">
        <Chip
          label="Todos"
          onClick={onClearFilter}
          variant={!activeFilter && timeRange[0] === 0 && timeRange[1] === 24 ? "filled" : "outlined"}
          deleteIcon={<ClearIcon />}
          onDelete={(activeFilter || timeRange[0] > 0 || timeRange[1] < 24) ? onClearFilter : undefined}
          sx={{
            backgroundColor: (!activeFilter && timeRange[0] === 0 && timeRange[1] === 24) ? '#6B7280' : 'transparent',
            color: (!activeFilter && timeRange[0] === 0 && timeRange[1] === 24) ? 'white' : '#6B7280',
            borderColor: '#6B7280',
            '&:hover': {
              backgroundColor: (!activeFilter && timeRange[0] === 0 && timeRange[1] === 24) ? '#6B7280' : `#6B728022`,
            }
          }}
        />
        {filterConfig.map((filter) => (
          <Chip
            key={filter.value}
            label={filter.label}
            onClick={() => onFilterClick(filter.value)}
            variant={activeFilter === filter.value ? "filled" : "outlined"}
            sx={{
              backgroundColor: activeFilter === filter.value ? filter.color : 'transparent',
              color: activeFilter === filter.value ? 'white' : filter.color,
              borderColor: filter.color,
              '&:hover': {
                backgroundColor: activeFilter === filter.value ? filter.color : `${filter.color}22`,
              }
            }}
          />
        ))}
      </Stack>

      <Box className="report-list__filters-right">
        <IconButton 
          onClick={onTimeFilterClick}
          className={`report-list__time-filter ${(timeRange[0] > 0 || timeRange[1] < 24) ? 'report-list__time-filter--active' : ''}`}
        >
          <AccessTimeIcon />
        </IconButton>
      </Box>
    </Box>
  );
};

FilterBar.propTypes = {
  activeFilter: PropTypes.string,
  timeRange: PropTypes.arrayOf(PropTypes.number).isRequired,
  onFilterClick: PropTypes.func.isRequired,
  onClearFilter: PropTypes.func.isRequired,
  onTimeFilterClick: PropTypes.func.isRequired,
};

export default FilterBar;
