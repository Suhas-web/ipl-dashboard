import { React } from "react";
import { Link } from "react-router-dom";
import "./MatchDetailCard.scss";
export const MatchDetailCard = ({ teamName, match }) => {
  if (!match) return null;
  const otherTeam = match.team1 == teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isMatchWon = teamName === match.matchWinner;
  return (
    <div
      className={
        isMatchWon ? "MatchDetailCard won-card" : "MatchDetailCard lost-card"
      }
    >
      <div className="short-detail">
        <h3>Latest Matches</h3>
        <h1>
          vs <Link to={otherTeamRoute}>{otherTeam}</Link>
        </h1>
        <h1></h1>
        <h2>{match.date}</h2>
        <h3>at {match.venue}</h3>
        <h4>
          {match.matchWinner} won by {match.resultMargin} {match.result}
        </h4>
      </div>
      <div className="additional-details">
        <h4>First Innings </h4>
        <p>{match.team1}</p>
        <h4>Second Innings</h4>
        <p>{match.team2}</p>
        <h4>Player of the match</h4>
        <p>{match.playerOfMatch}</p>
        <h4>Umpires</h4>
        <p>
          {match.umpire1}, {match.umpire2}
        </p>
      </div>
    </div>
  );
};
