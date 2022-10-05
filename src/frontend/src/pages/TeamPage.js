import { React, useEffect, useState } from "react";
import { MatchDetailCard } from "../components/MatchDetailCard";
import { MatchSmallCard } from "../components/MatchSmallCard";
import { useParams } from "react-router-dom";
import "./TeamPage.scss";
import { Link } from "react-router-dom";
import { PieChart } from "react-minimal-pie-chart";
export const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams();
  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`http://localhost:8080/teams/${teamName}`);
      const data = await response.json();
      setTeam(data);
    };
    fetchData();
  }, [teamName]);

  return team === null || team.teamName === null ? (
    <h1>No Team Found</h1>
  ) : (
    <div className="TeamPage">
      <div className="team-name">
        <h1>{team.teamName}</h1>
      </div>
      <div className="wins-losses">
        <h3>Wins / Losses</h3>
        <PieChart
          data={[
            {
              title: "wins",
              value: team.totalWins,
              color: "rgb(75, 154, 75)",
            },
            {
              title: "losses",
              value: team.totalMatches - team.totalWins,
              color: "rgb(194, 59, 59)",
            },
          ]}
        />
      </div>
      <div className="mid-section">
        <div className="match-detail">
          <MatchDetailCard teamName={teamName} match={team.matches[0]} />
        </div>
      </div>
      {team.matches.slice(1).map((match) => (
        <MatchSmallCard key={match.id} teamName={teamName} match={match} />
      ))}
      <a className="more-link">
        <Link
          to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}
        >
          <h2>More&gt;</h2>
        </Link>
      </a>
    </div>
  );
};
