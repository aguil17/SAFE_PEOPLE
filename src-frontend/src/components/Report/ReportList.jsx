import PropTypes from "prop-types";
import { useState, useMemo } from "react";
import { Box } from "@mui/material";
import "./ReportList.scss";
import SearchBar from "./SearchBar";
import FilterBar from "./components/FilterBar";
import TimeFilter from "./components/TimeFilter";
import IncidentCard from "./components/IncidentCard";

const ReportList = ({ incidents, onDelete, deleting }) => {
  const [expandedId, setExpandedId] = useState(null);
  const [activeFilter, setActiveFilter] = useState(null);
  const [timeRange, setTimeRange] = useState([0, 24]);
  const [anchorEl, setAnchorEl] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");

  const handleExpandClick = (id) => {
    setExpandedId(expandedId === id ? null : id);
  };

  const handleFilterClick = (filterValue) => {
    setActiveFilter(activeFilter === filterValue ? null : filterValue);
  };

  const clearFilter = () => {
    setActiveFilter(null);
    setTimeRange([0, 24]);
  };

  const handleTimeFilterClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleTimeFilterClose = () => {
    setAnchorEl(null);
  };

  const handleTimeRangeChange = (event, newValue) => {
    setTimeRange(newValue);
  };

  // Convertir hora en formato "HH:mm:ss" a número decimal
  const timeToDecimal = (timeStr) => {
    const [hours, minutes] = timeStr.split(':').map(Number);
    return hours + minutes / 60;
  };

  const formatTimeLabel = (value) => {
    const hours = Math.floor(value);
    const minutes = Math.round((value - hours) * 60);
    return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}`;
  };

  const filteredIncidents = useMemo(() => {
    return incidents.filter((incident) => {
      const matchesSearch = searchTerm === "" || 
        incident.descriptionIncident?.toLowerCase().includes(searchTerm.toLowerCase());
      const matchesFilter = !activeFilter || activeFilter === incident.incidentType;
      const incidentTime = timeToDecimal(incident.time);
      return matchesSearch && matchesFilter && incidentTime >= timeRange[0] && incidentTime <= timeRange[1];
    });
  }, [incidents, searchTerm, activeFilter, timeRange]);

  return (
    <Box>
      <SearchBar onSearch={setSearchTerm} />
      
      <FilterBar
        activeFilter={activeFilter}
        timeRange={timeRange}
        onFilterClick={handleFilterClick}
        onClearFilter={clearFilter}
        onTimeFilterClick={handleTimeFilterClick}
      />

      <TimeFilter
        open={Boolean(anchorEl)}
        anchorEl={anchorEl}
        timeRange={timeRange}
        onClose={handleTimeFilterClose}
        onTimeRangeChange={handleTimeRangeChange}
        formatTimeLabel={formatTimeLabel}
      />

      <Box className="report-list">
        {filteredIncidents.map((incident) => (
          <IncidentCard
            key={incident.id}
            incident={incident}
            expanded={expandedId === incident.id}
            onExpandClick={handleExpandClick}
            onDelete={onDelete}
            deleting={deleting}
          />
        ))}
      </Box>
    </Box>
  );
};

ReportList.propTypes = {
  incidents: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      incidentType: PropTypes.string.isRequired,
      date: PropTypes.string.isRequired,
      time: PropTypes.string.isRequired,
      cityName: PropTypes.string.isRequired,
      districtName: PropTypes.string.isRequired,
      descriptionIncident: PropTypes.string,
      photo: PropTypes.string,
      heridos: PropTypes.array,
      materiales: PropTypes.array,
    })
  ).isRequired,
  onDelete: PropTypes.func.isRequired,
  deleting: PropTypes.number,
};

export default ReportList;
