import { React, useEffect, useState } from "react";
import { MatchDetailCard } from "../components/MatchDetailCard";
// import { MatchSmallCard } from "../components/MatchSmallCard";
import { useParams } from "react-router-dom";
import "./MatchPage.scss";
import { YearSelector } from "../components/YearComponent";

export const MatchPage = () => {
  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();
  useEffect(() => {
    const getData = async () => {
      const response = await fetch(
        `${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}/matches/?year=${year}`
      );
      const data = await response.json();
      setMatches(data);
    };
    getData();
  }, [teamName, year]);
  return (
    <div className="MatchPage">
      <div className="year-selector">
        <YearSelector teamName={teamName} />
      </div>
      <div>
        <h1>
          {teamName} matches in {year}
        </h1>
        {matches.map((match) => (
          <MatchDetailCard key={match.date} teamName={teamName} match={match} />
        ))}
      </div>
    </div>
  );
};
