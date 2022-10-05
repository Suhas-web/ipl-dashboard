import { React } from "react";
import { Link } from "react-router-dom";
import "./YearSelector.scss";
export const YearSelector = ({ teamName }) => {
  let years = [];
  const startYear = process.env.REACT_APP_DATA_START_YEAR;
  const endYear = process.env.REACT_APP_DATA_END_YEAR;

  for (let i = startYear; i <= endYear; i++) {
    years.push(i);
  }

  return (
    <>
      <h4 className="heading">Select year</h4>
      <ol className="YearSelector">
        {years.map((year) => (
          <li key={year} className="li-selector">
            <Link to={`/teams/${teamName}/matches/${year}`}>{year}</Link>
          </li>
        ))}
      </ol>
    </>
  );
};
